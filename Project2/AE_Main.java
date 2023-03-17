import java.util.Iterator;
import java.util.Random;
import java.util.zip.DataFormatException;

public class AE_Main {

	public static void main(String[] args) {

		AE_RedBlackTree<Integer> tree = new AE_RedBlackTree<Integer>();
		AE_Validator valid = new AE_Validator();
		
		try {
			System.out.println(valid.isValid("3624553940"));
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
		
		Random rand = new Random(15);
		for (int i=0; i<10; i++) {
			int randNum = rand.nextInt(100);
			tree.insert(randNum);
		}
		System.out.println(tree.toInOrderString());
		
		int remove = 70;
		System.out.println("Removing: " + remove);
		tree.remove(remove);
		
		//Iterator test
		int match = 70;
		Iterator<Integer> iter = tree.iterator();
		while (iter.hasNext()) {
			int key = iter.next();
			System.out.println(key);
			if (key == match) {
				System.out.println("Found!");
				break;
			}
		}
	}

}
