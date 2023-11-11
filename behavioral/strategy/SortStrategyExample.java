package behavioral.strategy;

import java.util.List;
import java.util.ArrayList;

// Interface for the Strategy
interface SortingStrategy<T extends Comparable<T>> {
    List<T> sort(List<T> list);
}

class MergeSortDescending<T extends Comparable<T>> implements SortingStrategy<T> {
    @Override
    public List<T> sort(List<T> list) {
        if (list.size() <= 1) {
            return list;
        }
        int mid = list.size() / 2;
        List<T> left = sort(list.subList(0, mid));
        List<T> right = sort(list.subList(mid, list.size()));
        return merge(left, right);
    }

    private List<T> merge(List<T> left, List<T> right) {
        List<T> result = new ArrayList<>();
        int leftIndex = 0, rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) > 0) {
                result.add(left.get(leftIndex));
                leftIndex++;
            } else {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        result.addAll(left.subList(leftIndex, left.size()));
        result.addAll(right.subList(rightIndex, right.size()));

        return result;
    }
}

class QuickSortAscending<T extends Comparable<T>> implements SortingStrategy<T> {
    @Override
    public List<T> sort(List<T> list) {
        if (list.size() <= 1) {
            return list;
        }
        quickSort(list, 0, list.size() - 1);
        return list;
    }

    private void quickSort(List<T> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private int partition(List<T> list, int low, int high) {
        T pivot = list.get(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                T temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        T temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }
}

// The Context, use the strategy
class Sorter<T extends Comparable<T>> {
    private SortingStrategy<T> strategy;

    public Sorter(SortingStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortingStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public List<T> sort(List<T> list) {
        return strategy.sort(list);
    }
}

// Maybe this is unnecessary. If the language supports first-order functions, you can
// send the comparable function.

public class SortStrategyExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(9);
        numbers.add(1);
        numbers.add(7);

        Sorter<Integer> sorter = new Sorter<Integer>(new MergeSortDescending<>());
        List<Integer> sortedNumbers = sorter.sort(numbers);
        System.out.println("Merge Sort Descending: " + sortedNumbers);

        sorter.setStrategy(new QuickSortAscending<>());
        sortedNumbers = sorter.sort(numbers);
        System.out.println("Quick Sort Ascending: " + sortedNumbers);
    }
}
