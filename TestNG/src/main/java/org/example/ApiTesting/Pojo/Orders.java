package org.example.ApiTesting.Pojo;

import java.util.List;

public class Orders {

    List<OrderDetail> orders;

    public List<OrderDetail> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDetail> orders) {
        this.orders = orders;
    }
}
