package org.project;

import java.util.Collection;
import java.util.Comparator;

@SuppressWarnings({"unchecked", "unused"})
public class CustomArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int arrayCapacity;
    private E[] array;
    private int size;

    public CustomArrayList() {
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
        this.arrayCapacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    public void add(int index, E element) {
        if (size > DEFAULT_CAPACITY) {
            increaseCapacity();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    public void addAll(Collection<? extends E> c) {
        int index = size - 1;
        for (E element : c) {
            add(index++, element);
        }
    }

    public void clear() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return array[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
    }

    public void remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                remove(i);
                break;
            }
        }
    }

    public void sort(Comparator<? super E> c) {
        quickSort(array, 0, size - 1, c);
    }

    private void quickSort(E[] array, int low, int high, Comparator<? super E> c) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, c);
            quickSort(array, low, pivotIndex - 1, c);
            quickSort(array, pivotIndex + 1, high, c);
        }
    }

    private int partition(E[] array, int low, int high, Comparator<? super E> c) {
        E pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (c.compare(array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void increaseCapacity() {
        int newCapacity = arrayCapacity * 2;
        E[] newArray = (E[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}
