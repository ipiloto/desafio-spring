package br.com.mercadolivre.desafiospring.util;

import br.com.mercadolivre.desafiospring.exception.SortUtilException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.List;

public abstract class SortUtil {

    public static Sort sortStringToSort(String[] sort) throws SortUtilException {
        try {
            List<Order> orders = new ArrayList<>();
            if (sort[0].contains("_")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split("_");
                    orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }
            return Sort.by(orders);
        }catch (Exception e) {
            throw new SortUtilException();
        }
    }

    private static Direction getSortDirection(String direction){
        if (!direction.toUpperCase().equals("ASC") && !direction.toUpperCase().equals("DESC")) throw new RuntimeException();
        return Direction.fromString(direction);
    }

}
