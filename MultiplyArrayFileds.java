package interviewquestions.facebook;

/**
 * Multiply all fields except it's own position.
 */
public class MultiplyArrayFileds {
    public int[] multiplyAray(int[] array) {
        if (array == null || array.length == 0)
            return array;

        int[] product = new int[array.length];

        int leftProduct = array[0];
        for (int i = 1; i < product.length; i++) {
            product[i] = leftProduct;
            leftProduct *= array[i];
        }

        int rightProduct = array[product.length - 1];
        for (int i = product.length - 2; i >= 0; i--) {
            product[i] = (i == 0 ? rightProduct : product[i] * rightProduct);
            rightProduct *= array[i];
        }

        return product;
    }

}
