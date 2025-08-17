public class Main {



    public static void main(String[] args) {
        Product[] products = new Product[] {
                new Product("Iphone", "Electronics", 500.0, 10, 20),
                new Product("Laptop", "Electronics", 1200.0, 5, 15),
                new Product("Sofa", "Home", 700.0, 3, 4),
                new Product("Silla", "Home", 80.0, 10, 7),
                new Product("Airpods", "Electronics", 100.0, 0, 25)
        };

        Product[] electronics = filtrarElectronics(products);
        ordenarPorNombre(electronics);
        System.out.println("Electronics disponibles:");
        for (int i = 0; i < electronics.length; i++) {
            System.out.println(electronics[i].name);
        }

        aumentarPreciosHome(products);

        String[] cats = new String[products.length];
        double[] revs = new double[products.length];
        int catCount = calcularIngresosPorCategoria(products, cats, revs);

        int maxIdx = indiceMax(revs, catCount);
        System.out.println("Categoria con mayor revenue: " + cats[maxIdx]);

        Product[] copia = copiar(products, products.length);
        ordenarPorPrecioYStock(copia);
        System.out.println("Productos ordenados:");
        String[] resultNames = new String[copia.length];
        for (int i = 0; i < copia.length; i++) {
            resultNames[i] = copia[i].name;
            System.out.println(resultNames[i]);
        }
    }

    static Product[] filtrarElectronics(Product[] products) {
        Product[] tmp = new Product[products.length];
        int k = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i].category.equals("Electronics") && products[i].stock > 0) {
                tmp[k++] = products[i];
            }
        }
        return copiar(tmp, k);
    }

    static void aumentarPreciosHome(Product[] products) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].category.equals("Home") && products[i].stock < 5) {
                products[i].price = products[i].price * 1.1;
            }
        }
    }

    static int calcularIngresosPorCategoria(Product[] products, String[] cats, double[] revs) {
        int count = 0;
        for (int i = 0; i < products.length; i++) {
            String c = products[i].category;
            int idx = indexOf(cats, count, c);
            if (idx == -1) {
                cats[count] = c;
                revs[count] = 0.0;
                idx = count;
                count++;
            }
            revs[idx] += products[i].price * products[i].unitsSold;
        }
        return count;
    }

    static int indexOf(String[] arr, int len, String value) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(value)) return i;
        }
        return -1;
    }

    static int indiceMax(double[] arr, int len) {
        int idx = 0;
        for (int i = 1; i < len; i++) {
            if (arr[i] > arr[idx]) idx = i;
        }
        return idx;
    }

    static void ordenarPorNombre(Product[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].name.compareTo(arr[j + 1].name) > 0) {
                    Product t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
    }

    static void ordenarPorPrecioYStock(Product[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                boolean swap = false;
                if (arr[j].price < arr[j + 1].price) swap = true;
                else if (arr[j].price == arr[j + 1].price && arr[j].stock > arr[j + 1].stock) swap = true;
                if (swap) {
                    Product t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
    }

    static Product[] copiar(Product[] arr, int n) {
        Product[] r = new Product[n];
        for (int i = 0; i < n; i++) r[i] = arr[i];
        return r;
    }
}

class Product {
    String name;
    String category;
    double price;
    int stock;
    int unitsSold;

    Product(String name, String category, double price, int stock, int unitsSold) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.unitsSold = unitsSold;
    }
}
