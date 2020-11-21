//package com.inyta.assciation.config;
//
//import org.influxdb.InfluxDB;
//import org.influxdb.InfluxDBFactory;
//import org.influxdb.impl.InfluxDBMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Author: zhangwei
// * @Date: 2020/9/24 11:43
// */
//@Configuration
//public class InfluxDbConfig {
//
//    @Bean
//    public InfluxDB influxdb(){
//        return InfluxDBFactory.connect("106.14.170.74:8086","root","root");
//    }
//    @Bean
//    public InfluxDBMapper influxDBMapper(){
//        return new InfluxDBMapper(influxdb());
//    }
//}
