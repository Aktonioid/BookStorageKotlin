package com.bookStorage.core.repositories

import com.bookStorage.core.models.OrderModel
import java.util.UUID

interface IOrderRepo {
    fun CreateOrder(orderModel: OrderModel)
    fun UpdateOrder(orderModel: OrderModel)
    fun DelteOrderById(id: UUID)
    fun GetOrderById(id: UUID)
    fun GetAllOrdersByPage(page: Int, pageSize: Int)

    /**
     * Get allOrders by userFullName
     * @param name - String  is userFullName in OrderModel
     */
    fun GetOrdersByName(name: String)
}