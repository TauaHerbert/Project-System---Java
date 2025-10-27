package Utils;

import java.util.List;

@FunctionalInterface
public interface ComboBoxFilter<T> {
	List<T> filtrar(String texto);
}
