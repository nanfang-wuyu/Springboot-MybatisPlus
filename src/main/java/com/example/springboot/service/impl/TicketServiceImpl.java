package com.example.springboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.*;
import com.example.springboot.mapper.TicketMapper;
import com.example.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {



    @Autowired
    private TicketService ticketService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RateService rateService;

    @Autowired
    private TrainStationService trainStationService;

    @Autowired
    private StationService stationService;


    /*public boolean ensureNoConflict(List<Passenger> passengerList, Date deDate, Date arDate,
                             Time deTime, Time arTime){

        boolean noConflict = true;
        for(Passenger passenger : passengerList) {
            QueryWrapper<Ticket> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("passenger_id", passenger.getCardId());
            queryWrapper.eq("ticket_status","unfinished");
            List<Ticket> ticketList = ticketService.list(queryWrapper);
            List<BigInteger> orderIds = new ArrayList<>();
            for(Ticket ticket : ticketList){
                orderIds.add(ticket.getOrderId());
            }
            List<Order> orderList = orderService.listByIds(orderIds);

            if(ticketList.size()==0) continue;
            for(Ticket ticket : ticketList){
                if(ticket.get)
            }
        }

    }*/

    @Override
    public void addTicket(List<Passenger> passengerList, BigInteger userId, String seatType,
                   Info trainInfo, boolean isPaid, double price){

        if(passengerList.size() == 0) return;

        String status = "";
        if(isPaid) status = "paid";
        else status = "unpaid";


        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        java.sql.Time sqlTime = new java.sql.Time(date.getTime());


        double totalPrice = 0.0;
        double[] priceArr = new double[passengerList.size()];
        double studentRate = rateService.list().get(0).getRateStudent();
        for(int i = 0;i<passengerList.size();i++){
            if(passengerList.get(i).isStudent()){
                priceArr[i] = twoDigits(price * studentRate);
            }else {
                priceArr[i] = twoDigits(price);
            }
            totalPrice += priceArr[i];
        }

        totalPrice = twoDigits(totalPrice);

        BigInteger orderId = orderService.addOrder(userId,sqlDate,sqlTime,status,totalPrice);


        for(int i = 0;i<passengerList.size();i++){

            Passenger passenger  = passengerList.get(i);

            Ticket ticket = new Ticket();

            ticket.setOrderId(orderId);
            ticket.setTrainId(trainInfo.getTrainId());
            ticket.setTicketPrice(priceArr[i]);
            ticket.setDepartStation(trainInfo.getDeID());
            ticket.setArriveStation(trainInfo.getArID());
            ticket.setTicketStatus(status);
            ticket.setPassengerId(passenger.getCardId());
            ticket.setPassengerName(passenger.getName());
            ticket.setSeatType(seatType);
            if(passenger.isStudent()) ticket.setTicketType("student");
            else ticket.setTicketType("adult");

            ticketService.save(ticket);
        }




    }

    public double twoDigits(double d){
        BigDecimal b = new BigDecimal(d);
        return b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Override
    public JSONObject queryOrderTickets(BigInteger orderId){

        JSONObject jsonObject = new JSONObject();
        QueryWrapper<Ticket> ticketQueryWrapper = new QueryWrapper<>();
        ticketQueryWrapper.eq("order_id",orderId);
        List<Ticket> ticketList = ticketService.list(ticketQueryWrapper);
        String name = stationService.getStationById(ticketList.get(0).getArriveStation()).getStationName();
        jsonObject.put("arStation",name);
        List<Passenger> passengerList = new ArrayList<>();
        List<Double> priceList = new ArrayList<>();
        List<BigInteger> ticketIds = new ArrayList<>();
        for(Ticket ticket : ticketList) {
            boolean isStudent = false;
            if(ticket.getSeatType().equals("student")) isStudent = true;
            passengerList.add(new Passenger(ticket.getPassengerName(),
                    ticket.getPassengerId(), isStudent));
            priceList.add(ticket.getTicketPrice());
            ticketIds.add(ticket.getTicketId());
        }


        jsonObject.put("passengerList",passengerList);
        jsonObject.put("priceList",priceList);
        jsonObject.put("ticketIdList",ticketIds);

        Order order = orderService.getById(orderId);
        jsonObject.put("createDate",order.getCreateDate());


        Ticket ticket = ticketList.get(0);
        QueryWrapper<Train_station> train_stationQueryWrapper = new QueryWrapper<>();
        train_stationQueryWrapper.eq("train_id",ticket.getTrainId())
                .eq("station_id",ticket.getArriveStation());
        Train_station train_station = trainStationService.getOne(train_stationQueryWrapper,false);
        jsonObject.put("arDate",train_station.getEachArriveDate());
        jsonObject.put("arTime",train_station.getEachArriveTime());
        return jsonObject;
    }



    @Override
    public boolean cancelTicket(BigInteger ticketId){

        Ticket ticket = ticketService.getById(ticketId);

        Train_station train_station = trainStationService.getById(ticket.getTrainId());

        if(ticket.getTicketStatus().equals("paid")){
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            java.sql.Time sqlTime = new java.sql.Time(date.getTime());
            if(sqlDate.equals(train_station.getEachDepartDate())){
                if(train_station.getEachDepartTime().getTime()/60/1000-sqlTime.getTime()/60/1000
                        <30){
                    return false;
                }
            }else if(sqlDate.after(train_station.getEachDepartDate()))
                return false;
        }

        ticket.setTicketStatus("cancelled");
        ticketService.updateById(ticket);
        return true;
    }

}
