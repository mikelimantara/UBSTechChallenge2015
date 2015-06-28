package com.prototype.ubs.techchallenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.prototype.ubs.techchallenge.R;
import com.prototype.ubs.techchallenge.model.OrderStatus;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 28/6/2015.
 */
public class OrderStatusFragment extends Fragment {

    private View v;
    private ListView orderStatusList;
    private OrderStatusListAdapter orderStatusListAdapter;
    private List<OrderStatus> orderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.order_status_list, container, false);
        prepareData();
        initViews();

        return v;
    }

    private void prepareData() {
        OrderStatus order1 = new OrderStatus();
        order1.setStatus(OrderStatus.Status.PENDING);
        order1.setTransactionType(OrderStatus.TransactionType.BUY);
        order1.setAccountName("Account 1");
        order1.setOrderAmount(135200.50);
        order1.setOrderDate(new DateTime(2015, 6, 20, 9, 30, 0));

        OrderStatus order2 = new OrderStatus();
        order2.setStatus(OrderStatus.Status.PENDING);
        order2.setTransactionType(OrderStatus.TransactionType.SELL);
        order2.setAccountName("Account 2");
        order2.setOrderAmount(200000.50);
        order2.setOrderDate(new DateTime(2015, 6, 20, 8, 30, 0));

        OrderStatus order3 = new OrderStatus();
        order3.setStatus(OrderStatus.Status.EXECUTED);
        order3.setTransactionType(OrderStatus.TransactionType.BUY);
        order3.setAccountName("Account 1");
        order3.setOrderAmount(100000.10);
        order3.setOrderDate(new DateTime(2015, 6, 19, 8, 40, 0));

        OrderStatus order4 = new OrderStatus();
        order4.setStatus(OrderStatus.Status.PENDING);
        order4.setTransactionType(OrderStatus.TransactionType.SWITCH);
        order4.setAccountName("Account 3");
        order4.setOrderAmount(135200.50);
        order4.setOrderDate(new DateTime(2015, 6, 19, 8, 30, 0));

        OrderStatus order5 = new OrderStatus();
        order5.setStatus(OrderStatus.Status.CANCELLED);
        order5.setTransactionType(OrderStatus.TransactionType.SELL);
        order5.setAccountName("Account 1");
        order5.setOrderAmount(200200.50);
        order5.setOrderDate(new DateTime(2015, 6, 17, 6, 30, 0));

        orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);
    }

    private void initViews() {
        orderStatusList = (ListView) v.findViewById(R.id.order_status_listview);
        orderStatusListAdapter = new OrderStatusListAdapter(orderList);
        orderStatusList.setAdapter(orderStatusListAdapter);
    }

    private class OrderStatusListAdapter extends BaseAdapter {

        private DateTimeFormatter dateFormat;
        private NumberFormat currencyFormatter;
        private LayoutInflater inflater;
        private List<OrderStatus> orderList;

        public OrderStatusListAdapter(List<OrderStatus> orderList) {
            inflater = LayoutInflater.from(getActivity());
            this.orderList = orderList;

            currencyFormatter = NumberFormat.getCurrencyInstance();
            dateFormat = new DateTimeFormatterBuilder()
                    .appendDayOfMonth(1)
                    .appendLiteral(" ")
                    .appendMonthOfYearText()
                    .appendLiteral(" ")
                    .appendYear(4,4).toFormatter();
        }

        @Override
        public int getCount() {
            return orderList.size();
        }

        @Override
        public Object getItem(int position) {
            return orderList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                v = inflater.inflate(R.layout.order_status_item, parent, false);
            }

            OrderStatus orderStatus = (OrderStatus) getItem(position);

            TextView txtOrderDate = (TextView) v.findViewById(R.id.order_status_date);
            TextView txtOrderAccount = (TextView) v.findViewById(R.id.order_status_account);
            TextView txtOrderDesc = (TextView) v.findViewById(R.id.order_status_desc);
            TextView txtOrderStatus = (TextView) v.findViewById(R.id.order_status);

            txtOrderDate.setText(orderStatus.getOrderDate().toString(dateFormat));
            txtOrderAccount.setText(orderStatus.getAccountName());
            txtOrderDesc.setText(orderStatus.getTransactionType().name() +
                    " by Amount " + currencyFormatter.format(orderStatus.getOrderAmount()));
            txtOrderStatus.setText(orderStatus.getStatus().name());

            return v;
        }
    }
}
