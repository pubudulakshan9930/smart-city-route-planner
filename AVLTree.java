class AVLNode {
    String key;
    AVLNode left, right;
    int height;

    AVLNode(String key) {
        this.key = key;
        height = 1;
    }
}

public class AVLTree {
    private AVLNode root;

    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    private int balanceFactor(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode t2 = x.right;
        x.right = y;
        y.left = t2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode t2 = y.left;
        y.left = x;
        x.right = t2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public void insert(String key, Graph graph) {
        root = insert(root, key, graph);
    }

    private AVLNode insert(AVLNode node, String key, Graph graph) {
        if (node == null) {
            graph.addLocation(key); // Map to graph after tree insert
            return new AVLNode(key);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key, graph);
        } else if (cmp > 0) {
            node.right = insert(node.right, key, graph);
        } else {
            System.out.println("Duplicate location ignored: " + key);
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = balanceFactor(node);

        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return leftRotate(node);
        }
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public void delete(String key, Graph graph) {
        root = delete(root, key, graph);
    }

    private AVLNode delete(AVLNode node, String key, Graph graph) {
        if (node == null) return node;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key, graph);
        } else if (cmp > 0) {
            node.right = delete(node.right, key, graph);
        } else {
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    graph.removeLocation(key); // Remove from graph
                    return null;
                } else {
                    return temp;
                }
            } else {
                AVLNode temp = minValueNode(node.right);
                node.key = temp.key;
                node.right = delete(node.right, temp.key, graph);
            }
        }

        if (node == null) return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = balanceFactor(node);

        if (balance > 1 && balanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        if (balance > 1 && balanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && balanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        if (balance < -1 && balanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public void displayLocations() {
        if (root == null) {
            System.out.println("No locations to display.");
            return;
        }
        System.out.print("Sorted locations: ");
        inOrder(root);
        System.out.println();
    }

    private void inOrder(AVLNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    public boolean contains(String key) {
        AVLNode current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) return true;
            current = cmp < 0 ? current.left : current.right;
        }
        return false;
    }
}