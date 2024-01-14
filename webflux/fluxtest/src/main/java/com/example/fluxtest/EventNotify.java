package com.example.fluxtest;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventNotify {

      private List<String> events = new ArrayList<>();

      private boolean isChanged = false;

      public void add(String data) {
            events.add(data);
            isChanged = true;
      }

      public boolean getChange() {
            return isChanged;
      }

      public void setChanged(boolean changed) {
            isChanged = changed;
      }

      public List<String> getEvents() {
            return events;
      }

}
