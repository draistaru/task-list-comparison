package utils;

import model.Item;

import java.util.List;

public class ListComparisonResult {
    private final List<Item> missingItems;
    private final List<Item> unexpectedItems;

    public ListComparisonResult(List<Item> missingItems, List<Item> unexpectedItems) {
        this.missingItems = missingItems;
        this.unexpectedItems = unexpectedItems;
    }

    public List<Item> getMissingItems() {
        return missingItems;
    }

    public List<Item> getUnexpectedItems() {
        return unexpectedItems;
    }

    public boolean isSuccessful() {
        return missingItems.isEmpty() && unexpectedItems.isEmpty();
    }

    @Override
    public String toString() {
        return String.format(
            "Comparison failed:\nMissing items:\n%s\nUnexpected items:\n%s",
            formatList(missingItems), formatList(unexpectedItems)
        );
    }

    private String formatList(List<Item> items) {
        if (items.isEmpty()) return "  (none)";
        return items.stream()
                .map(item -> "  - " + item.toString())
                .reduce("", (acc, str) -> acc + str + "\n");
    }
}
