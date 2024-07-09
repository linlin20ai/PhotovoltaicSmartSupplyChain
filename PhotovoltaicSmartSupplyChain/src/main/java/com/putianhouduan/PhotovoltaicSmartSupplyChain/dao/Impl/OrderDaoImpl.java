package com.putianhouduan.PhotovoltaicSmartSupplyChain.dao.Impl;

import com.putianhouduan.PhotovoltaicSmartSupplyChain.common.api.CommonResult;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dao.OrderDao;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.Order;
import com.putianhouduan.PhotovoltaicSmartSupplyChain.dto.preOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 林圣涛
 */
@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
/*
* 获取当前时间
* */
     private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");

    @Override
    public List<Order> getAcceptedOrders(int id) {
        String sql="SELECT * FROM orders WHERE buyer_id= "+id+" OR "+" seller_id = "+ id ;
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Order.class));
    }

    @Override
    public Order getOrderById(int id) {
        String sql="SELECT * FROM orders WHERE order_id= ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Order.class),id);
    }

    @Override
    public int orderReturn(int id) {
        return 0;
    }

    @Override
    public CommonResult postOrder(int id, String item, int quantity, double pricePerUnit,String location) {
        Date date=new Date(System.currentTimeMillis());
        String DATE=simpleDateFormat.format(date);
        String sql="INSERT INTO pre_orders (\n" +
                "    seller_id, \n" +
                "    item, \n" +
                "    quantity, \n" +
                "    price_per_unit, \n" +
                "    expected_transaction_date, \n" +
                "    location, \n" +
                "    status\n" +
                ") VALUES (?,?,?,?,?,?,?)";
        int res=jdbcTemplate.update(sql,id,item,quantity,pricePerUnit,DATE,location,"pending");
        if(res>0){
            return CommonResult.success(200,"创建成功！");
        }
        return CommonResult.failed();
    }

    @Override
    public preOrder getPreOrderById(int preOrderId) {
        String sql="SELECT * FROM pre_orders where pre_order_id = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(preOrder.class),preOrderId);
    }

    @Override
    public CommonResult addOrder(int buyerId, int sellerId, String item, int quantity, double pricePerUnit, String location, String status) {
        Date date=new Date(System.currentTimeMillis());
        String DATE=simpleDateFormat.format(date);
        String sql=" INSERT INTO orders (\n" +
                "    buyer_id, \n" +
                "    seller_id, \n" +
                "    item, \n" +
                "    quantity, \n" +
                "    price_per_unit, \n" +
                "    transaction_date, \n" +
                "    location, \n" +
                "    status\n" +
                ")"+"VALUES(?,?,?,?,?,?,?,?)";
        int res=jdbcTemplate.update(sql,buyerId,sellerId,item,quantity,pricePerUnit,DATE,location,status);
        if(res>0){
            return CommonResult.success(200,"创建成功");
        }
        return CommonResult.failed();
    }

    @Override
    public CommonResult changePreOrder(int preOrderId,String status) {
         String sql="UPDATE pre_orders\n" +
                 "SET status = ?  \n" +
                 "WHERE pre_order_id = ? ;  \n";
        int res =  jdbcTemplate.update(sql,status,preOrderId);
        if(res>0){
            return CommonResult.success(200,"修改成功");
        }
        return CommonResult.failed();
    }

}
