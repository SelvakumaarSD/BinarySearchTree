package bstreconstruction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BSTReconstructor {
	static BST<Integer> originalBST;
	static BST<Integer> preOrderReconstructedBST = new BST<>();
	static BST<Integer> postOrderReconstructedBST = new BST<>();

	static List<Integer> inOrderOutput;
	static List<Integer> preOrderOutput;
	static List<Integer> postOrderOutput;

	static Integer[] input = { 35, 25, 65, 30, 15, 20, 95, 45, 40, 55, 32, 60, 75 };

	public static void main(String[] args) {

		// Create BST from input
		originalBST = new BST<>(input);

		// Generate in/pre/post order traversal lists
		generateBSTTraversalLists();

		// Reconstruct BST from traversal lists of original BST
		System.out.println("Reconstructing BSTs from pre and post order traversal lists.");
		preOrderReconstructor(preOrderOutput);
		postOrderReconstructor(postOrderOutput);

		reconstructionVerificationTests();

	}

	// Generate in/pre/post order traversal lists
	private static void generateBSTTraversalLists() {
		inOrderOutput = originalBST.inorder();
		System.out.println("In-order: " + inOrderOutput);

		preOrderOutput = originalBST.preorder();
		System.out.println("Pre-order: " + preOrderOutput);

		postOrderOutput = originalBST.postorder();
		System.out.println("Post-order: " + postOrderOutput);

	}

	private static void reconstructionVerificationTests() {

		System.out.println("preOrderReconstructedBST should be true: " + originalBST.isEqualTo(preOrderReconstructedBST));
		System.out.println("postOrderReconstructedBST should be true: " + originalBST.isEqualTo(postOrderReconstructedBST));

		System.out.println("Inserting and deleting 65 from original BST");
		originalBST.delete(65);
		originalBST.insert(65);

		System.out.println("preOrderReconstructedBST should be false: " + originalBST.isEqualTo(preOrderReconstructedBST));
		System.out.println("postOrderReconstructedBST should be false: " + originalBST.isEqualTo(postOrderReconstructedBST));

		System.out.println("Inserting and deleting 65 from preOrderReconstructed BST");
		preOrderReconstructedBST.delete(65);
		preOrderReconstructedBST.insert(65);

		System.out.println("preOrderReconstructedBST should be true: " + originalBST.isEqualTo(preOrderReconstructedBST));
		System.out.println("postOrderReconstructedBST should be false: " + originalBST.isEqualTo(postOrderReconstructedBST));

		System.out.println("Inserting and deleting 65 from postOrderReconstructed BST");
		postOrderReconstructedBST.delete(65);
		postOrderReconstructedBST.insert(65);

		System.out.println("preOrderReconstructedBST should be true: " + originalBST.isEqualTo(preOrderReconstructedBST));
		System.out.println("postOrderReconstructedBST should be true: " + originalBST.isEqualTo(postOrderReconstructedBST));
	}

	// Reconstruct BST from pre-order traversal lists of original BST.
	// This method will take an inputArray of pre-order traversal items
	// and re-create the original BST, and save reconstructed tree in the
	// preOrderReconstructedBST variable.
	private static void preOrderReconstructor(List<Integer> inputArray) {

		int rootElement = inputArray.get(0);
		preOrderReconstructedBST.add(rootElement);

		List<Integer> leftArray = new ArrayList();
		List<Integer> rightArray = new ArrayList();

		for (int i = 1; i < inputArray.size(); i++) {
			if (inputArray.get(i) < rootElement) {
				leftArray.add(inputArray.get(i));
			} else if (inputArray.get(i) > rootElement) {
				rightArray.add(inputArray.get(i));
			}
		}
		leftArray.addAll(rightArray);

		for (int i = 0; i < leftArray.size(); i++) {
			preOrderReconstructedBST.add(leftArray.get(i));
		}
	}

	// Reconstruct BST from post-order traversal lists of original BST.
	// This method will take an inputArray of post-order traversal items
	// and re-create the original BST, and save reconstructed tree in the
	// postOrderReconstructedBST variable.
	private static void postOrderReconstructor(List<Integer> inputArray) {
		int lastIndex = inputArray.size()-1;
		int rootElement = inputArray.get(lastIndex);
		postOrderReconstructedBST.add(rootElement);

		List<Integer> leftSub = new ArrayList();
		List<Integer> rightSub = new ArrayList();

		for (int i = lastIndex-1; i >= 0; i--) {
			if (inputArray.get(i) < rootElement) {
				leftSub.add(inputArray.get(i));
			} else if (inputArray.get(i) > rootElement) {
				rightSub.add(inputArray.get(i));
			}
		}
		rightSub.addAll(leftSub);

		for (int i = 0; i < rightSub.size(); i++) {
			postOrderReconstructedBST.add(rightSub.get(i));
		}
	}

}
