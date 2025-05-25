package utils;

import model.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ListComparator {

    public static ListComparisonResult compare(List<Item> expectedList, List<Item> actualList) {
        List<Item> missingItems = expectedList.stream()
                .filter(e -> actualList.stream().noneMatch(a -> a.equals(e)))
                .collect(Collectors.toList());

        List<Item> unexpectedItems = actualList.stream()
                .filter(a -> expectedList.stream().noneMatch(e -> e.equals(a)))
                .collect(Collectors.toList());

        return new ListComparisonResult(missingItems, unexpectedItems);
    }
}
