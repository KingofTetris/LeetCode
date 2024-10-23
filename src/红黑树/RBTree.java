package 红黑树;


public class RBTree<K extends Comparable<K> , V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    // 红黑树的 root 节点
    private RBNode root;

    public RBNode getRoot() {
        return root;
    }

    public void setRoot(RBNode root) {
        this.root = root;
    }

    /**
     * 实现左旋
     *     p             pr
     *    /\            /\
     *   pl pr  ==>    p rr
     *      /\        /\
     *     rl rr    pl rl
     *  左旋操作：
     *     p-pl 和 pr-rr 的关系不需要调整
     *     需要调整的情况
     *     1. pr-rl 调整为 p-rl
     *        将rl变为p的右子节点
     *        将p设置为rl的父节点
     *     2.判断p是否有父节点
     *       有
     *          pr.parent = p.parent
     *          pr为 p.parent的子节点，到底是 左子节点还是右子节点呢？
     *          if p.parent.left == p
     *              p.parent.left = r
     *           else
     *              p.parent.right = r
     *       没有
     *         直接把pr设置为 root节点
     *      3.最后 p和pr 交换
     *        p.parent = r
     *        r.left = p
     *
     * @param p
     */
    private void leftRotate(RBNode p){
        if(p != null){
            // 获取到 pr 节点
            RBNode r = p.right;
            // 情况1：pr-rl 调整为 p-rl
            p.right = r.left;
            if(r.left != null){
                r.left.parent = p;
            }
            // 情况2： 判断p节点是否有父节点
            r.parent = p.parent; // 不管p是否存在父节点，我们都设置p的父节点也为 pr的父节点
            if(p.parent == null){
                // 说明p就是root节点，这时 pr 会变为新的root节点
                root = r;
            }// 说明p节点是存在父节点的
            else if(p.parent.left == p){
                // 说明p是父节点的左子节点，那么 pr 也肯定是p的父节点的左子节点
                p.parent.left = r;
            }else {
                p.parent.right = r;
            }
            // 情况3： 设置p为pr的左子节点
            r.left = p;
            p.parent = r;
        }
    }


    /**
     * 实现右旋
     *     p             pr
     *    /\            /\
     *   pl pr  <==    p rr
     *      /\        /\
     *     rl rr    pl rl
     *  左旋操作：
     *     p-pl 和 pr-rr 的关系不需要调整
     *     需要调整的情况
     *     1. pr-rl 调整为 p-rl
     *        将rl变为p的右子节点
     *        将p设置为rl的父节点
     *     2.判断p是否有父节点
     *       有
     *          pr.parent = p.parent
     *          pr为 p.parent的子节点，到底是 左子节点还是右子节点呢？
     *          if p.parent.left == p
     *              p.parent.left = r
     *           else
     *              p.parent.right = r
     *       没有
     *         直接把pr设置为 root节点
     *      3.最后 p和pr 交换
     *        p.parent = r
     *        r.left = p
     *
     * @param p
     */
    private void rightRotate(RBNode p){
        if(p != null){
            // 获取到 pr 节点
            RBNode r = p.left;
            // 情况1：pr-rl 调整为 p-rl
            p.left = r.right;
            if(r.right != null){
                r.right.parent = p;
            }
            // 情况2： 判断p节点是否有父节点
            r.parent = p.parent; // 不管p是否存在父节点，我们都设置p的父节点也为 pr的父节点
            if(p.parent == null){
                // 说明p就是root节点，这时 pr 会变为新的root节点
                root = r;
            }// 说明p节点是存在父节点的
            else if(p.parent.left == p){
                // 说明p是父节点的左子节点，那么 pr 也肯定是p的父节点的左子节点
                p.parent.left = r;
            }else {
                p.parent.right = r;
            }
            // 情况3： 设置p为pr的左子节点
            r.right = p;
            p.parent = r;
        }
    }

    /**
     * 红黑树 新增节点的操作
     * @param key
     * @param value
     */
    public void put(K key,V value){
        RBNode t = root;
        if(t == null){
            // 这是插入的第一个节点
            root = new RBNode(key,value == null ? key:value,null);
            return ;
        }
        int cmp;
        // 1. 找到插入的位置(找到新增节点的父节点)
        RBNode parent;
        do{
            parent = t;
            cmp = key.compareTo((K) t.key);
            if(cmp < 0){
                // 从左侧查找
                t = t.left;
            }else if(cmp > 0){
                // 从右侧查找
                t = t.right;
            }else{
                // 说明节点存在，用插入节点的值覆盖掉 相同的节点
                t.setValue(value==null?key:value);
                return ;
            }
        }while (t != null);
        // 2.将新节点添加到父节点的子节点中(左右子节点)
        // 创建要插入的节点
        RBNode node = new RBNode(key,value == null? key : value,parent);
        if(cmp < 0){
            // 将新节点添加到父节点的左侧
            parent.left = node;
        }else{
            // 添加到右侧
            parent.right = node;
        }
        // 旋转和变色 调整红黑树的平衡
        fixAfterPut(node);
    }

    private RBNode parentOf(RBNode node){
        return node != null ? node.parent :null;
    }

    private  RBNode leftOf(RBNode node){
        return node != null ? node.left:null;
    }

    private  RBNode rightOf(RBNode node){
        return node != null ? node.right :null;
    }

    private boolean colorOf(RBNode node){
        return node == null ? BLACK:node.color;
    }

    private void setColor(RBNode node,boolean color){
        if(node != null){
            node.setColor(color);
        }
    }


    /**
     * 插入节点后的调整操作
     * 2-3-4对应的操作
     *   2节点： 新插入一个元素 直接和2节点合并 不用调整
     *       红黑树：新增一个红色节点在黑色节点下 不需要调整
     *   3节点： 新插入一个元素在3节点下 那么会出现6中情况(2两种不需要调整，4中需要调整)
     *       红黑树： 插入的节点是 插入在 上黑下红的结构中，插入在红色节点
     *   4节点： 新插入一个元素在4节点下，那么会出现4中情况 都需要调整
     *       红黑树：新增的节点是红色 爷爷节点是黑色，父亲节点和叔叔节点是红色
     * @param x
     */
    private void fixAfterPut(RBNode<K,Object> x){
        // 插入的节点 肯定红色
        x.color = RED;
        // 2节点不用调整，3,4节点才需要调整
        while( x != null && x != root && x.parent.color == RED){
           if(parentOf(x) == leftOf(parentOf(parentOf(x)))){
                // 需要调整的只剩下4种情况，有叔叔节点和没有叔叔节点
                // 获取插入节点的叔叔节点
               RBNode y = rightOf(parentOf(parentOf(x)));
               if(colorOf(y) == RED){
                   // 说明是有叔叔节点的
                   // 变色+递归
                   // 父亲和叔叔变为黑色  爷爷变为红色
                   setColor(parentOf(x),BLACK);
                   setColor(y,BLACK);
                   setColor(parentOf(parentOf(x)),RED);
                   // 递归处理
                   x = parentOf(parentOf(x));
               }else{
                    // 说明是没有叔叔节点的
                   if(x == parentOf(x).right){
                       x = parentOf(x);
                       leftOf(x);
                   }
                   // 父亲节点变为黑色 爷爷节点变为红色
                   setColor(parentOf(x),BLACK);
                   setColor(parentOf(parentOf(x)),RED);
                   // 根据爷爷节点做右旋操作
                   rightRotate(parentOf(parentOf(x)));
               }

           }else{
                // 需要调整的也是4中情况，刚好和上面的4中情况左右相反
               // 需要调整的只剩下4种情况，有叔叔节点和没有叔叔节点
               // 获取插入节点的叔叔节点
               RBNode y = leftOf(parentOf(parentOf(x)));
               if(colorOf(y) == RED){
                   // 说明是有叔叔节点的
                   // 变色+递归
                   // 父亲和叔叔变为黑色  爷爷变为红色
                   setColor(parentOf(x),BLACK);
                   setColor(y,BLACK);
                   setColor(parentOf(parentOf(x)),RED);
                   // 递归处理
                   x = parentOf(parentOf(x));
               }else{
                   // 说明是没有叔叔节点的
                   if(x == parentOf(x).left){
                       x = parentOf(x);
                       rightOf(x);
                   }
                   // 父亲节点变为黑色 爷爷节点变为红色
                   setColor(parentOf(x),BLACK);
                   setColor(parentOf(parentOf(x)),RED);
                   // 根据爷爷节点做右旋操作
                   leftRotate(parentOf(parentOf(x)));
               }
           }
        }
        // root 节点肯定为黑色
        root.color = BLACK;
    }


    /**
     * 找到node的前驱节点
     *    找到指定节点的前驱节点，就是找到这个节点的左侧的最大值
     * @param node
     * @return
     */
    private RBNode predecessor(RBNode node){

        if(node == null){
            return null;
        }else if(leftOf(node) != null){
            // 左子节点不为空，那么我就根据这个左子节点找他的最右的子节点即可
            RBNode p = leftOf(node);
            while (rightOf(p) != null){
                p = rightOf(p);
            }
            return p;
        }else{
            // node节点没有左子节点 那么我们就只能向上去找前驱节点
            // 这种情况在删除 红黑树 2-3-4树中不会出现
            RBNode p = node.parent;
            RBNode ch = node;
            while (p != null && ch == leftOf(p)){
                ch = p;
                p = parentOf(p);
            }
            return  p;
        }
    }

    /**
     * 后继节点
     * @param node
     * @return
     */
    private RBNode successor(RBNode node){

        if(node == null){
            return null;
        }else if(rightOf(node) != null){
            // 左子节点不为空，那么我就根据这个左子节点找他的最右的子节点即可
            RBNode p = rightOf(node);
            while (leftOf(p) != null){
                p = leftOf(p);
            }
            return p;
        }else{
            // node节点没有左子节点 那么我们就只能向上去找前驱节点
            // 这种情况在删除 红黑树 2-3-4树中不会出现
            RBNode p = node.parent;
            RBNode ch = node;
            while (p != null && ch == rightOf(p)){
                ch = p;
                p = parentOf(p);
            }
            return  p;
        }



    }


    static class RBNode<K extends Comparable<K> ,V>{

        private RBNode parent;

        private RBNode left;

        private RBNode right;

        private boolean color;

        private K key;

        private V value;


        public RBNode() {
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode( K key, V value,RBNode parent) {
            this.parent = parent;
            this.key = key;
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }


}
