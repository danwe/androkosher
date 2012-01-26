package com.androk;

public class Item {
	
        // Item's name
        private String _namecity;
        // Item's description
        private String _namerest;
        // Item's price
        private double _x;
        private double _y;
 
        /*
         * Constructor
         */
        public Item(double x, double y, String namerest, String namecity) {
                _x = x;
                _y = y;
                _namerest = namerest;
                _namecity = namecity;
                
                
        }	
 
        // Get item's name
        public double getx() {
                return _x;
        }
 
        // Get item's price
        public double gety() {
                return _y;
        }
        public String getrest() {
            return _namerest;
    }
        public String getcity() {
            return _namecity;
    }
 
        // Print item details
      //  public void printItem() {
        //        System.out.println("Item: "+_name);
          //      System.out.println("Description: "+_description);
            //    System.out.println("Price: "+_price);
        //}
}

