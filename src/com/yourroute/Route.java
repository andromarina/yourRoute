package com.yourroute;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/18/13
 * Time: 3:44 PM
 * To change this template use File | Settings | File Templates.
 */


public class Route {
    private final int id;
    private final String name;
    private final carType type;
    private final String startEnd;

    public Route (int id, String name, carType type, String startEnd ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.startEnd = startEnd;
    }

    public enum carType {
        BUS (1),
        TROLLEYBUS {
            public String toString() {
               return "Троллейбус";
            }
        },
        TRAM {
            public String toString() {
                return "Трамвай";
            }
        },
        MINIBUS {
             public String toString() {
                 return "Маршрутка";
             }
        },
        SHUTTLEBUS {
             public String toString() {
                 return "Пригородный";
             }
        }

    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public carType getType() {
        return this.type;
    }

    public String getStartEnd() {
        return this.startEnd;
    }

    public carType stringToCarType(String s) {
        if (s == "Автобус") {
           return carType.BUS;
        }


    }
}
