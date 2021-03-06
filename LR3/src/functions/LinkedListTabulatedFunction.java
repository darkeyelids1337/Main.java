package functions;

public class LinkedListTabulatedFunction implements TabulatedFunction {
    private class FunctionNode {
        private FunctionPoint point = null;
        private FunctionNode prev = null, next = null;
    }

    private int len, cur;
    private FunctionNode head = new FunctionNode(), headtemp, tail, curr;

    {
        head.next = head;
        head.prev = head;
        headtemp = head;
        tail = head;
        curr = head;
    }

    FunctionNode getNodeByIndex(int index) {
        int fromHead = index;
        int fromTail = len - index - 1;
        int fromCur = Math.abs(cur - index);
        if (fromHead < fromTail) {
            if (fromHead < fromCur) {
                curr = head;
                cur = 0;
            }
        } else if (fromTail < fromCur) {
            curr = tail;
            cur = len - 1;
        }
        if (index < cur) {
            while (cur != index) {
                curr = curr.prev;
                cur--;
            }
        } else {
            while (cur != index) {
                curr = curr.next;
                cur++;
            }
        }
        return curr;
    }

     FunctionNode addNodeToTail() {
        tail.next = new FunctionNode();
        curr = tail.next;
        curr.prev = tail;
        tail = tail.next;
        tail.next = head;
        head.prev = tail;
        len++;
        return curr;
    }

    FunctionNode addNodeByIndex(int index) {
        getNodeByIndex(index);
        FunctionNode node = new FunctionNode();
        node.next = curr;
        node.prev = curr.prev;
        curr.prev.next = node;
        curr.prev = node;
        curr = node;
        len++;
        return curr;
    }

    FunctionNode deleteNodeByIndex(int index) {
        getNodeByIndex(index);
        FunctionNode temp = curr;
        temp.prev.next = curr.next;
        temp.next.prev = curr.prev;
        curr = curr.prev;
        cur--;
        len--;
        return temp;
    }

    public LinkedListTabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (leftX >= rightX || pointsCount < 2) {
            try {
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("???????????? ????????????");
            }
        }
        double prom = (rightX - leftX) / (pointsCount - 1);
        cur = 0;
        len = pointsCount;
        cur++;
        double x = leftX;
        for (int i = 0; i < pointsCount; i++) {
            headtemp.next = new FunctionNode();
            curr = headtemp.next;
            curr.prev = headtemp;
            headtemp = headtemp.next;
            headtemp.point = new FunctionPoint(x, 0);
            tail = headtemp;
            tail.next = head;
            cur++;
            x += prom;
        }
    }
    public LinkedListTabulatedFunction(double leftX, double rightX, double[] values) {
        if (leftX >= rightX || values.length < 2) {
            try {
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("???????????? ????????????");
            }
        }
        double prom = (rightX - leftX) / (values.length - 1);
        len = values.length;
        double x = leftX;
        for (int i = 0; i < len; i++) {
            headtemp.next = new FunctionNode();
            curr = headtemp.next;
            curr.prev = headtemp;
            headtemp = headtemp.next;
            headtemp.point = new FunctionPoint(x, values[i]);
            tail = headtemp;
            tail.next = head;
            cur++;
            x += prom;
        }
    }
    public double getLeftDomainBorder()
    {
        return head.next.point.x;
    }
    public double getRightDomainBorder()
    {
        return tail.point.x;
    }
    public double getFunctionValue(double X)
    {
        if (X >= this.getLeftDomainBorder() && X <= this.getRightDomainBorder()){
            headtemp = head.next;
            for(int i = 0; i < len; i++){
                if (X == headtemp.point.x){
                    return headtemp.point.y;
                }
                headtemp = headtemp.next;
            }
            while(X > headtemp.point.x){
                headtemp = headtemp.next;
            }
            double k = (headtemp.point.y - headtemp.prev.point.y)/(headtemp.point.x - headtemp.prev.point.x);
            double m = headtemp.point.y - k * headtemp.point.x;
            return k * X+ m;
        }
        return Double.NaN;
    }
    public int getPointsCount()
    {
        return len;
    }
    public FunctionPoint getPoint(int index)
    {
        headtemp = head.next;
        if (index > len || index < 0)
        {
            try {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            catch (FunctionPointIndexOutOfBoundsException e) {
                System.out.println("?????????? ???? ??????????????");
            }
        }
        for ( int i = 0; i < index; i++)
        {
            headtemp = headtemp.next;
        }
        return headtemp.point;
    }
    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException {
        if (index > len || index < 0) {
            try {
                throw new FunctionPointIndexOutOfBoundsException();
            } catch (FunctionPointIndexOutOfBoundsException e) {
                System.out.println("?????????? ???? ??????????????");
            }
        }
        headtemp = head.next;
        for ( int i = 0; i < index; i++)
        {
            headtemp = headtemp.next;
        }
        if(point.x  >=  headtemp.prev.point.x && point.x  <= headtemp.next.point.x){

            headtemp.point.x = point.x;
            headtemp.point.y = point.y;
        }
        else {
            throw new InappropriateFunctionPointException();
        }

    }
    public double getPointX(int index)
    {
        headtemp = head.next;
        if (index > len|| index < 0) {
            try {
                throw new FunctionPointIndexOutOfBoundsException();
            } catch (FunctionPointIndexOutOfBoundsException e) {
                System.out.println("?????????? ???? ??????????????");
            }
        }
        for (int i = 0; i < index; i++)
        {
            headtemp = headtemp.next;
        }
        return headtemp.point.x;
    }
    public void setPointX(int index, double X) throws InappropriateFunctionPointException {
        headtemp = head.next;
        if (index > len || index < 0) {
            try {
                throw new FunctionPointIndexOutOfBoundsException();
            } catch (FunctionPointIndexOutOfBoundsException e) {
                System.out.println("?????????? ???? ??????????????");
            }
        }
        for (int i = 0; i < index; i++)
        {
            headtemp = headtemp.next;
        }
        if (X>=headtemp.prev.point.x && X <= headtemp.next.point.x )
        {
            headtemp.point.x = X;
        }
        else throw new InappropriateFunctionPointException();
    }
    public double getPointY(int index)
    {
        if (index > len || index < 0) {
            try {
                throw new FunctionPointIndexOutOfBoundsException();
            } catch (FunctionPointIndexOutOfBoundsException e) {
                System.out.println("?????????? ???? ??????????????");
            }
        }
        headtemp = head.next;
        for (int i = 0; i < index; i++)
        {
            headtemp = headtemp.next;
        }
        return headtemp.point.y;
    }
    public void setPointY(int index, double Y)
    {
        if (index > len|| index < 0) {
            try {
                throw new FunctionPointIndexOutOfBoundsException();
            } catch (FunctionPointIndexOutOfBoundsException e) {
                System.out.println("?????????? ???? ??????????????");
            }
        }
        headtemp = head.next;
        for (int i = 0; i < index; i++)
        {
            headtemp = headtemp.next;
        }
        headtemp.point.y = Y;
    }
    public void deletePoint(int index)
    {
        if (index > len || index < 0) {
            try {
                throw new FunctionPointIndexOutOfBoundsException();
            } catch (FunctionPointIndexOutOfBoundsException e) {
                System.out.println("?????????? ???? ??????????????");
            }
        }
        if (len < 3)
        {
            throw new IllegalStateException();
        }
        else {
           deleteNodeByIndex(index);
        }
    }
    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        if (point.x >= this.getLeftDomainBorder() && point.x <= this.getRightDomainBorder())
        {
            headtemp = head.next;
            int i = 0;
            while (point.x > headtemp.point.x)
            {
                headtemp = headtemp.next;
                i++;
            }
            if (point.x == headtemp.point.x)
            {
                throw new InappropriateFunctionPointException();
            }
            else {
               headtemp = addNodeByIndex(i);
               headtemp.point = point;
            }
        }
    }
}

