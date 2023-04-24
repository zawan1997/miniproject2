package vttp.csf.finalproject.server.Services;

import java.util.List;

public interface MainServiceInt<T> {

    public T create(T req);
	public T update(T req);
	public List<T> get();
	public T get(int id);
	public String delete(int id);
}
