package dao;

import dto.VendingItem;

import java.util.ArrayList;
import java.util.HashMap;

public class VendingDaoFileImpl implements VendingDao {

    private HashMap<String,VendingItem> items;



    @Override
    public VendingItem getItem(String name) {
        return null;
    }

    @Override
    public ArrayList<VendingItem> getAllItem() {
        return null;
    }
}
