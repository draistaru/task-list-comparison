package test.java.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import model.Item;
import utils.ListComparator;
import utils.ListComparisonResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class StepDefinitions {

    private List<Item> expectedList = new ArrayList<>();
    private List<Item> actualList = new ArrayList<>();
    private ListComparisonResult comparisonResult;

    @Given("I have the following items in the first list:")
    public void i_have_the_following_items_in_the_first_list(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            expectedList.add(new Item(
                    row.get("name"),
                    new BigDecimal(row.get("price")),
                    row.get("category")
            ));
        }
    }

    @Given("I have the following items in the second list:")
    public void i_have_the_following_items_in_the_second_list(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            actualList.add(new Item(
                    row.get("name"),
                    new BigDecimal(row.get("price")),
                    row.get("category")
            ));
        }
    }

    @When("I compare both lists")
    public void i_compare_both_lists() {
        comparisonResult = ListComparator.compare(expectedList, actualList);
    }

    @Then("the lists should contain the same items with name, price, and category, regardless of order")
    public void the_lists_should_contain_the_same_items_with_name_price_and_category_regardless_of_order() {
        assertTrue(comparisonResult.toString(), comparisonResult.isSuccessful());
    }
}
