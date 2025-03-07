/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class Cart {

    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();

    }

    public List<Item> getItems() {
        return items;
    }

    private Item getItemById(int id) {
        for (Item i : items) {
            if (i.getProduct().getId() == id) {
                return i;
            }
        }
        return null;
    }

    public int getQuantityByID(int id) {
        return getItemById(id).getQuantity();
    }

    public void addItem(Item t) {
        if (getItemById(t.getProduct().getId()) != null) {
            Item m = getItemById(t.getProduct().getId());
            m.setQuantity(m.getQuantity() + t.getQuantity());
        } else {
            items.add(t);
        }
    }

    public void removeItem(int id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    public int getTotalMoneny() {
        int t = 0;
        for (Item i : items) {
            t += (i.getQuantity() * i.getPrice());
        }
        return t;
    }

    public Product getProductById(int id, List<Product> list) {
        for (Product p : list) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public Cart(String txt, List<Product> list) {
        items = new ArrayList<>();
        try {
            if (txt != null && txt.startsWith("null")) {
                txt = txt.substring(4); // Loại bỏ "null"
            }

            if (txt != null && txt.length() != 0) {
                String[] s = txt.split("/");
                for (String i : s) {
                    String[] n = i.split(":");

                    if (n.length == 2) {
                        int id = Integer.parseInt(n[0]);
                        int quantity = Integer.parseInt(n[1]);
                        Product p = getProductById(id, list);
                        if (p != null) {
//                            Item t = new Item(p, quantity, p.getPrice() * 2);
//                            addItem(t);
                        }
                    } else {

                        System.out.println("Dữ liệu không hợp lệ: " + i);
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi khi chuyển đổi số: " + e.getMessage());
        }
    }

}
