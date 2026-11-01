
import java.util.LinkedList;

class BracketsChecker {

    // task 4,
    // 4
    // проверка корректности расстановки круглых скобок
    // O(n)
    static boolean checkBrackets(String str) {
        Stack stack = new Stack();

        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            }
            if (c == ')') {
                if (stack.pop() == null) {
                    return false;
                }
            }
        }

        return stack.size() == 0;
    }

    // task 4,
    // 5
    // проверка корректности расстановки любых скобок
    // O(n)
    static boolean checkAllBrackets(String str) {
        Stack stack = new Stack();

        for (char c : str.toCharArray()) {

            if (c == '(') {
                stack.push(c);
            }
            if (c == ')') {
                if (stack.peek() == null || !stack.pop().equals('(')) {
                    return false;
                }
            }

            if (c == '{') {
                stack.push(c);
            }
            if (c == '}') {
                if (stack.peek() == null || !stack.pop().equals('{')) {
                    return false;
                }
            }

            if (c == '[') {
                stack.push(c);
            }
            if (c == ']') {
                if (stack.peek() == null || !stack.pop().equals('[')) {
                    return false;
                }
            }
        }

        return stack.size() == 0;
    }
}

class StackExtended extends Stack<Integer> {

    public LinkedList<Integer> minimalList;
    public LinkedList<Float> averageList;

    StackExtended() {
        super();
        minimalList = new LinkedList();
        averageList = new LinkedList();
    }

    // task 4,
    // 6
    // минимальный элемент в стеке
    // O(1)
    Integer getSmallestElement() {
        if (this.size == 0) {
            return null;
        }

        return (int) this.minimalList.peek();
    }

    // task 4,
    // 7
    // среднее в стеке
    // O(1)
    Float getAverage() {
        if (this.size == 0) {
            return null;
        }

        return this.averageList.peek();
    }

    // task 4,
    // 6,7
    // pop()
    // O(1)
    public Integer pop() {
        // ваш код
        if (this.size == 0) {
            return null;// если стек пустой
        }

        Integer result = (Integer) this.list.pop();
        if (this.minimalList.peek().equals(result)) {
            this.minimalList.pop();
        }

        Float currSum = this.averageList.pop() * this.size - result;

        this.size--;
        if (size != 0) {
            this.averageList.push(currSum / size);
        }

        return result;
    }

    // task 4,
    // 6,7
    // push()
    // O(1)
    public void push(Integer val) {
        if (val == null) {
            return;
        }

        this.list.push(val);
        Float currSum = this.averageList.peek() == null ? val : this.averageList.pop() * this.size + val;

        if (this.minimalList.peek() == null || (val != null && this.minimalList.peek() >= val)) {
            this.minimalList.push(val);
        }

        this.size++;
        this.averageList.push(currSum / this.size);
    }

    // task 4,
    // 6,7
    // peek()
    // O(1)
    public Integer peek() {
        return (Integer) this.list.peek();
    }
}

class Postfix {

    // task 4,
    // 8
    // исчисление постфикса
    // O(n)
    static int calculate(String postfix) {
        Stack<String> symbols = new Stack<String>();
        Stack<Integer> numbers = new Stack<Integer>();

        for (String c : postfix.split(" ")) {
            symbols.push(c);
        }

        int size = symbols.size();
        for (int i = 0; i < size; i++) {
            String el = symbols.popFirst();
            if (el.equals("+")) {
                Integer first = numbers.pop();
                Integer second = numbers.pop();

                numbers.push(first + second);
                continue;
            }
            if (el.equals("*")) {
                Integer first = numbers.pop();
                Integer second = numbers.pop();

                numbers.push(first * second);
                continue;
            }

            numbers.push(Integer.parseInt(el));
        }

        return numbers.pop();
    }
}

// рефлексия 2.9 предложенный вариант эффективнее моей реализации по памяти, комплексность та же, в целом более оптимально, запомню.
// рефлексия 2.10 Цикл по длине списка очень хороший подход, но если в списке нет параметра размера, а он вычисляется каждый раз при вызове size(), то тут бесконечное выполнение, что косвенно само по себе говорит о цикле. В моей реализации свойства int size не было, поэтому просто проверяю, что голова не равна хвосту. однако проболема моей реализации в том, что она находит только циклы размером в 1 ноду.
// рефлексия 2.11 У меня реализоавна сортировка вставкой, в целом более оптимальный вариант, чем пузырёк, хотя разницы большой нет.
// рефлексия 2.12 Моя реализация совпадает по большей части с предложенной, но только для двух входных списков. Минусом такого подхода является то, что входящие листы сортируются в моей реализации.abstract.abstract. 
// рефлексия 2.13 Проверка на класс вместо флага действительно лучший подход, идея с одной и той же нодой в хвосте и голове мне очень понравилась, приму во внимание.

