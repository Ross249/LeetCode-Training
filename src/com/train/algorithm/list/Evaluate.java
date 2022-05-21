package com.train.algorithm.list;

import java.util.Stack;

public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        while (!In.isEmpty()){
            String s = In.readString();
            if(s.equals("("));
            else if (s.equals("+")|| s.equals("-")||s.equals("/")||s.equals("*")) {
                ops.push(s);

            } else if (s.equals(")")) {
                String op = ops.pop();
                double v = vals.pop();
                if(op.equals("+")){
                    v = vals.pop() + v;
                } else if (op.equals("-")){
                    v = vals.pop() - v;
                } else if (op.equals("*")){
                    v = vals.pop() * v;
                } else if (op.equals("/")){
                    v = vals.pop() / v;
                } else if (op.equals("sqrt")){
                    v = Math.sqrt(v);
                }
                vals.push(v);
            }else {
                vals.push(Double.parseDouble(s));
            }
        }
    }
}
