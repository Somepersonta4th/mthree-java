package dao;

import dto.VendingItem;

import java.util.ArrayList;

public interface VendingDao {

    public VendingItem getItem(String name);

    public ArrayList<VendingItem> getAllItem();

}
