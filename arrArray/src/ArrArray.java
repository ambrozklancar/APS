public class ArrArray {
    Element[][] elements;

    public ArrArray(){
        elements = new Element[1][1];
    }

    public void insert(int e){
        if(dvojnik(e)){
            return;
        }
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[i].length; j++) {
                if(elements[i][j] != null){
                    if(elements[i][j].counter == 0){
                        elements[i][j] = new Element(e);
                        for (Element[] element : elements) {
                            if (element[0] != null) {
                                quickSort(element, 0, element.length - 1);
                            }
                        }
                        return;
                    }
                }
            }
        }
        if(elements[0][0] == null){
            elements[0][0] = new Element(e);
            return;
        }
        else {
            for (int i = 0; i < elements.length; i++) {
                if(elements[i][0] == null){
                    Element[] elementsTemp = new Element[(int)Math.pow(2,i)];
                    int temp = 0;
                    for (int j = 0; j < elements.length; j++) {
                        if(elements[j][0] == null)
                            break;
                        for (int k = 0; k < elements[j].length; k++) {
                            elementsTemp[temp] = elements[j][k];
                            elements[j][k] = null;
                            temp++;
                        }
                    }
                    for (int j = 0; j < elementsTemp.length; j++){
                        if(elementsTemp[j] == null)
                            break;
                        elements[i][j] = elementsTemp[j];
                    }
                    elements[i][(int)Math.pow(2,i)-1] = new Element(e);
                    for (Element[] element : elements) {
                        if (element[0] != null) {
                            quickSort(element, 0, element.length - 1);
                        }
                    }
                    return;
                }
            }
            Element[][] elementsTemp = new Element[elements.length+1][];
            for(int i = 0; i < elementsTemp.length; i++){
                elementsTemp[i] = new Element[(int)Math.pow(2,i)];
            }


            int temp = 0;
            for (int j = 0; j < elements.length; j++) {
                for (int k = 0; k < elements[j].length; k++) {
                    elementsTemp[elementsTemp.length-1][temp] = elements[j][k];
                    temp++;
                }

            }
            elementsTemp[elementsTemp.length-1][temp] = new Element(e);
            elements = elementsTemp;
        }
        for (Element[] element : elements) {
            if (element[0] != null) {
                quickSort(element, 0, element.length - 1);
            }
        }

    }

    public void printOut(){
        boolean izpis = true;
        if(vsiPrazni()){
            System.out.println("empty");
            return;
        }
        if(elements.length == 1 && elements[0][0] == null)
            System.out.println("empty");
        else {
            for (int j = 0; j < elements.length; j++) {
                for (int k = 0; k < elements[j].length; k++) {
                    if(k == 0){
                        System.out.print("A_" + j + ": ");
                    }
                    if(elements[j][k] == null && izpis){
                        System.out.print("...");
                        izpis = false;
                    }
                    else{
                        if(elements[j][k] == null){
                            break;
                        }
                        if(elements[j][k].counter == 0)
                            if(k != elements[j].length-1)
                                System.out.print("x, ");
                            else
                                System.out.print("x");
                        else
                        if(k != elements[j].length-1)
                            System.out.print(elements[j][k].element + "/" + elements[j][k].counter + ", ");
                        else
                            System.out.print(elements[j][k].element + "/" + elements[j][k].counter);
                    }
                }
                System.out.println();
                izpis = true;

            }
        }
    }

    public boolean dvojnik(int e){
        for(int i = 0;i < elements.length;i++){
            for(int j = 0; j < elements[i].length;j++){
                if(elements[i][j] != null && e == elements[i][j].element){
                    elements[i][j].counter++;
                    return true;
                }
            }
        }

        return false;
    }

    public void delete(int e){
        for(int i = 0;i < elements.length;i++){
            for(int j = 0; j < elements[i].length;j++){
                if(elements[i][j] == null)
                    break;
                if(e == elements[i][j].element && elements[i][j].counter > 0){
                    elements[i][j].counter--;
                    System.out.println("true");
                    return;
                }
            }
        }
        System.out.println("false");
    }
    public void find(int e){
        for(int i = 0;i < elements.length;i++){
            for(int j = 0; j < elements[i].length;j++){
                if(elements[i][j] == null)
                    break;
                if(e == elements[i][j].element && elements[i][j].counter > 0){
                    System.out.println("true");
                    return;
                }

            }
        }
        System.out.println("false");
    }
    static void swap(Element[] arr, int i, int j)
    {
        Element temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // This function takes last element as pivot,
    // places the pivot element at its correct position
    // in sorted array, and places all smaller to left
    // of pivot and all greater elements to right of pivot
    static int partition(Element[] arr, int low, int high)
    {
        // Choosing the pivot
        int pivot = arr[high].element;

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller than the pivot
            if (arr[j].element < pivot) {

                // Increment index of smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    // The main function that implements QuickSort
    // arr[] --> Array to be sorted,
    // low --> Starting index,
    // high --> Ending index
    static void quickSort(Element[] arr, int low, int high)
    {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public boolean vsiPrazni(){
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[i].length; j++) {
                if(elements[i][j] != null){
                    if(elements[i][j].counter != 0){
                        return false;
                    }
                }
            }

        }
        return true;
    }
}



class Element{
    int element;
    int counter;

    public Element(int e){
        element = e;
        counter = 1;
    }

}

