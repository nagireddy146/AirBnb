package com.airBnb_application.firstproject.Services;

import com.airBnb_application.firstproject.Entities.Room;

public interface InventoryService {

     void generateInventoryOfRoomFor1Year(Room room);

     void deleteFutureInventoryOfRoom(Room room);
}
