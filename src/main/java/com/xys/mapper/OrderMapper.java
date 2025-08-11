package com.xys.mapper;

import com.xys.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单数据访问层接口
 */
@Mapper
public interface OrderMapper {
    
    /**
     * 插入新订单
     */
    int insertOrder(Order order);
    
    /**
     * 根据订单ID查询订单
     */
    Order selectOrderById(@Param("orderId") Long orderId);
    
    /**
     * 根据订单编号查询订单
     */
    Order selectOrderByOrderSn(@Param("orderSn") String orderSn);
    
    /**
     * 根据用户ID查询订单列表
     */
    List<Order> selectOrdersByUserId(@Param("userId") Long userId);
    
    /**
     * 根据状态查询订单列表
     */
    List<Order> selectOrdersByStatus(@Param("status") Integer status);
    
    /**
     * 查询所有订单（分页）
     */
    List<Order> selectAllOrders(@Param("offset") int offset, @Param("limit") int limit);
    
    /**
     * 统计订单总数
     */
    int countOrders();
    
    /**
     * 根据用户ID统计订单数
     */
    int countOrdersByUserId(@Param("userId") Long userId);
    
    /**
     * 根据状态统计订单数
     */
    int countOrdersByStatus(@Param("status") Integer status);
    
    /**
     * 更新订单状态
     */
    int updateOrderStatus(@Param("orderId") Long orderId, @Param("status") Integer status);
    
    /**
     * 更新订单信息
     */
    int updateOrder(Order order);
    
    /**
     * 删除订单
     */
    int deleteOrder(@Param("orderId") Long orderId);
    
    /**
     * 根据条件搜索订单
     */
    List<Order> searchOrders(@Param("keyword") String keyword, 
                            @Param("status") Integer status,
                            @Param("offset") int offset, 
                            @Param("limit") int limit);
    
    /**
     * 根据条件统计搜索结果数量
     */
    int countSearchOrders(@Param("keyword") String keyword, @Param("status") Integer status);
}