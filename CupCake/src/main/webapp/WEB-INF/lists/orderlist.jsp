<%-- 
    Document   : orderlist
    Created on : Mar 5, 2019, 12:15:41 PM
    Author     : Andreas Vikke
--%>

<%@page import="logic.model.Order"%>
<%@page import="logic.OrderController"%>
<%@page import="java.util.List"%>

<%
    OrderController oc = new OrderController();
    List<Order> orders = oc.getAllOrders();
%>

<button class="btn btn-info">Sort By </button>
<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Username</th>
            <th scope="col">Date</th>
            <th scope="col"></th>
        </tr>
    </thead>
    <tbody id="orderList">
    </tbody>
</table>

<script>
    var orders = [<% for(Order o : orders) { out.println("["); out.println("\"" + o.getOrderId() + "\","); out.println("\"" + o.getUsername()+ "\","); out.println("\"" + o.getOrderDate()+ "\""); out.println("],"); } %>];
    
    orders.sort((function(index){
        return function(a, b){
            return (a[index] === b[index] ? 0 : (a[index] < b[index] ? -1 : 1));
        };
    })(0));
    
    orders.forEach(function(entry) {
        var parenttbl = document.getElementById("orderList");
        var newel = document.createElement('tr');
        entry.forEach(function(eentry) {
            var newtd = document.createElement('td');
            newtd.innerHTML = eentry;
            newel.appendChild(newtd);
        });
        var newtd = document.createElement('td');
        newtd.innerHTML = '<a href="${pageContext.request.contextPath}/order?orderId=' + entry[0] +'"><button class="btn btn-info">Show Order</button></a>';
        newtd.setAttribute('class', 'tableButton');
        newel.appendChild(newtd);
        parenttbl.appendChild(newel);
    });
</script>
