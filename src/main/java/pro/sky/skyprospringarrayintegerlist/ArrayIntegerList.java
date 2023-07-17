package pro.sky.skyprospringarrayintegerlist;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayIntegerList implements IntegerList {
    private Integer[] strings;

    public ArrayIntegerList() {
        this.strings = new Integer[0];
    }

    @Override
    public Integer add(Integer item) {
        if (item == null){
            throw new IllegalArgumentException();
        }
        Integer[] temp = strings;
        strings = new Integer[temp.length + 1];
        System.arraycopy(temp, 0, strings, 0, temp.length);
        strings[strings.length-1] = item;
        return strings[strings.length-1];
    }

    @Override
    public Integer add(int index, Integer item) {
        if (item == null){
            throw new IllegalArgumentException();
        }
        if (index > strings.length - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Integer[] firstPart = Arrays.copyOfRange(strings, 0, index);
        Integer[] secondPart = Arrays.copyOfRange(strings, index, strings.length);
        strings = new Integer[firstPart.length + secondPart.length + 1];
        System.arraycopy(firstPart, 0, strings, 0, firstPart.length);
        strings[index] = item;
        System.arraycopy(secondPart, 0, strings, index + 1, secondPart.length);
        return strings[index];
    }

    @Override
    public Integer set(int index, Integer item) {
        if (item == null){
            throw new IllegalArgumentException();
        }
        if (index > strings.length - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        strings[index] = item;
        return strings[index];
    }

    @Override
    public Integer remove(Integer item) {
        if (!Arrays.stream(strings).anyMatch(x -> item.equals(x))) {
            throw new NoSuchElementException();
        }
        int ind = 0;
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] == item) {
                ind = i;
                break;
            }
        }

        return remove(ind);
    }

    @Override
    public Integer remove(int index) {
        if (index > strings.length - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Integer item = strings[index];

        Integer[] firstPart = Arrays.copyOfRange(strings, 0, index);
        Integer[] secondPart = Arrays.copyOfRange(strings, index + 1, strings.length);
        strings = new Integer[firstPart.length + secondPart.length];
        System.arraycopy(firstPart, 0, strings, 0, firstPart.length);
        System.arraycopy(secondPart, 0, strings, index, secondPart.length);
        return item;
    }

    @Override
    public boolean contains(Integer item){
        Integer[] stringsCopy = toArray();
        sort(stringsCopy);
        return binarySearch(stringsCopy, item);
    }
    @Override
    public int indexOf(Integer item){
        if (!Arrays.stream(strings).anyMatch(x -> item.equals(x))) {
            return -1;
        }
        int ind = 0;
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] == item) {
                ind = i;
                break;
            }
        }
        return ind;
    }
    @Override
    public int lastIndexOf(Integer item){
        if (!Arrays.stream(strings).anyMatch(x -> item.equals(x))) {
            return -1;
        }
        int ind = 0;
        for (int i = strings.length-1; i >= 0; i--) {
            if (strings[i] == item) {
                ind = i;
                break;
            }
        }
        return ind;
    }

    public Integer get(int index) {
        if (index > strings.length - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return strings[index];
    }
    @Override
    public boolean equals(IntegerList otherList){
        return Arrays.equals(strings, otherList.toArray());
    }
    @Override
    public int size() {
        return strings.length;
    }
    @Override
    public boolean isEmpty() {
        if (strings.length == 0) {
            return true;
        } else return false;
    }
    @Override
    public void clear() {
        strings = new Integer[0];
    }
    @Override
    public Integer[] toArray(){
        return strings;
    }

    private void sort(Integer[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private boolean binarySearch(Integer[] arr, Integer item){
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
