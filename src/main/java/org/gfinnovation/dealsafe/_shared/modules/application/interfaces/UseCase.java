package org.gfinnovation.dealsafe._shared.modules.application.interfaces;

public interface UseCase<I, O> {
    O execute(I input);
}
