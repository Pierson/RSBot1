package core;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;

public abstract class Job extends MethodProvider {
        public Job(MethodContext ctx) {
                super(ctx);
        }

        public int delay() {
                return 250;
        }

        public int priority() {
                return 0;
        }

        public abstract boolean validate();

        public abstract void execute();
}