package blazesoft.hotelreservation.model;

import lombok.Getter;

@Getter
public enum RoomType {
    basic("basic", 4),
    suite("suite", 6),
    penthouse("penthouse", 8);
    
    private final String name;
    private final int maxPeople;

    private RoomType(String name, int maxPeople) {
        this.name = name;
        this.maxPeople = maxPeople;
    }

    @Override
    public String toString() {
        return getName();
    }

}