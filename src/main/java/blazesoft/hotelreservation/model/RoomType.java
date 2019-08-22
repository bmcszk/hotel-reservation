package blazesoft.hotelreservation.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static List<RoomType> getTypesForPeople(int people) {
        return Stream.of(values())
            .filter(rt -> rt.getMaxPeople() >= people)
            .collect(Collectors.toList());
    }

}