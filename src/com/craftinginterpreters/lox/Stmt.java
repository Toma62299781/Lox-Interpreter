/**
 * statement -> exprStmt
 *            | forStmt
 *            | ifStmt
 *            | printStmt
 *            | whileStmt
 *            | block;
 *
 * whileStmt -> "while" "(" expression ")" statement ;
 *
 * We list the grammar of for loop here, but we do not directly
 * implement it in this project. Cause we already have
 * while statement. We will implement for statement using a
 * method called desugaring, which means we will not add for
 * statement as a node in AST tree.
 *
 * forStmt   -> "for" "(" (varDecl | exprStmt| ";")
 *                     expression? ";"
 *                     expression? ")" statement ;
 */
package com.craftinginterpreters.lox;

import java.util.List;

abstract class Stmt {
    interface Visitor<R> {
        R visitBlockStmt(Block stmt);
        // R visitClassStmt(Class stmt);
        R visitExpressionStmt(Expression stmt);
        R visitFunctionStmt(Function stmt);
        R visitIfStmt(If stmt);
        R visitPrintStmt(Print stmt);
        // R visitReturnStmt(Return stmt);
        R visitVarStmt(Var stmt);
        R visitWhileStmt(While stmt);
    }
    // Nested Stmt classes here...

    //> stmt-expression
    static class Expression extends Stmt {
        Expression(Expr expression) {
            this.expression = expression;
        }

        <R> R accept(Visitor<R> visitor) {
            return visitor.visitExpressionStmt(this);
        }

        final Expr expression;
    }
    //< stmt-expression

    //> stmt-print
    static class Print extends Stmt {
        Print(Expr expression) {
            this.expression = expression;
        }

        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPrintStmt(this);
        }

        final Expr expression;
    }
    //< stmt-print

    //> stmt-var
    static class Var extends Stmt {
        Var(Token name, Expr initializer) {
            this.name = name;
            this.initializer = initializer;
        }

        <R> R accept(Visitor<R> visitor) {
            return visitor.visitVarStmt(this);
        }

        final Token name;
        final Expr initializer;
    }
    //< stmt-var

    //> stmt-block
    static class Block extends Stmt {
        Block(List<Stmt> statements) {
            this.statements = statements;
        }

        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBlockStmt(this);
        }

        final List<Stmt> statements;
    }
    //< stmt-block

    //> stmt-if
    static class If extends Stmt {
        If(Expr condition, Stmt thenBranch, Stmt elseBranch) {
            this.condition = condition;
            this.thenBranch = thenBranch;
            this.elseBranch = elseBranch;
        }

        <R> R accept(Visitor<R> visitor) {
            return visitor.visitIfStmt(this);
        }

        final Expr condition;
        final Stmt thenBranch;
        final Stmt elseBranch;
    }

    //> stmt-while
    static class While extends Stmt {
        While(Expr condition, Stmt body) {
            this.condition = condition;
            this.body = body;
        }

        <R> R accept(Visitor<R> visitor) {
            return visitor.visitWhileStmt(this);
        }

        final Expr condition;
        final Stmt body;
    }
    //< stmt-while

    //> stmt-function
    static class Function extends Stmt {
        Function(Token name, List<Token> parameters, List<Stmt> body) {
            this.name = name;
            this.parameters = parameters;
            this.body = body;
        }

        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFunctionStmt(this);
        }

        final Token name;
        final List<Token> parameters;
        final List<Stmt> body;
    }


    abstract <R> R accept(Visitor<R> visitor);
}
