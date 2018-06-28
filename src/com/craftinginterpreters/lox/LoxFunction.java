package com.craftinginterpreters.lox;

import java.util.List;

public class LoxFunction implements LoxCallable {
    private final Stmt.Function declaration;
    private final Environment closure;  // closure-field
    private final boolean isInitializer;

    LoxFunction(Stmt.Function declaration, Environment closure, boolean isInitializer) {
        this.declaration = declaration;
        this.isInitializer = isInitializer;
        this.closure = closure;
    }


    LoxFunction bind(LoxInstance instance) {
        Environment environment = new Environment(closure);
        environment.define("this", instance);
        return new LoxFunction(declaration, environment, isInitializer);
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
//        Environment environment = new Environment(interpreter.globals);
        //> call-closure
        Environment environment = new Environment(closure);
        //< call-closure

        for (int i = 0; i < declaration.parameters.size(); i++) {
            environment.define(declaration.parameters.get(i).lexeme, arguments.get(i));
        }

        // functions function-call < functions catch return
//        interpreter.executeBlock(declaration.body, environment);

        //> catch-return
        try {
            interpreter.executeBlock(declaration.body, environment);
        } catch (Return returnValue) {
            return returnValue.value;
        }
        //< catch-return

        if (isInitializer) return closure.getAt(0, "this"); // classes return-this
        return null;
    }

    @Override
    public int arity() {
        return declaration.parameters.size();
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + " >";
    }
}
