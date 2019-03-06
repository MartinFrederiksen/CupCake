package logic.model;

import logic.model.enums.CupcakePartEnum;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import logic.CupcakeController;

/**
 *
 * @author Martin Frederiksen
 */
public class ShoppingCart {

    private List<LineItem> lineItems;

    public ShoppingCart() {
        lineItems = new ArrayList();
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void addLineItem(LineItem li) {
        for (LineItem i : lineItems) {
            if (li.getBottom().getId() == i.getBottom().getId() && li.getTop().getId() == i.getTop().getId()) {
                i.addQuantity(li.getQuantity());
                return;
            }
        }
        lineItems.add(li);
    }

    public void removeLineItem(LineItem li, int qty) {
        //List<LineItem> toRemove = new ArrayList<LineItem>();
        for (LineItem i : lineItems) {
            if (li.getBottom().getId() == i.getBottom().getId() && li.getTop().getId() == i.getTop().getId()) {
                if (li.getQuantity() >= qty) {
                    li.subtractQuantity(qty);
                }
            }
        }
    }

    public int calculate() {
        List<LineItem> li = lineItems;
        int price = 0;
        for (LineItem i : li) {
            price += i.getPrice();
        }

        return price;
    }

    public static void main(String[] args) {
        ShoppingCart sc = new ShoppingCart();
        CupcakeController cc = new CupcakeController();
        LineItem li = new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 1), cc.getCupcakePart(CupcakePartEnum.TOP, 1), 2);
        sc.addLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 3), cc.getCupcakePart(CupcakePartEnum.TOP, 4), 1));
        //sc.addLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 1), cc.getCupcakePart(CupcakePartEnum.TOP, 1), 2));
        sc.addLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 3), cc.getCupcakePart(CupcakePartEnum.TOP, 4), 1));
        //sc.addLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 1), cc.getCupcakePart(CupcakePartEnum.TOP, 1), 2));
        sc.addLineItem(li);
        sc.addLineItem(li);
        sc.removeLineItem(li, 2);

        for (LineItem item : sc.getLineItems()) {
            System.out.println(item.getQuantity());
        }
    }
}
