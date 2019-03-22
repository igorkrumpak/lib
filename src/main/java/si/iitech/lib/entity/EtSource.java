package si.iitech.lib.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import si.iitech.lib.parser.IParser;

@MappedSuperclass
public abstract class EtSource<T extends IParser> extends EtEntity {
	
	private String url;
	private String title;
	private String parserClass;

	@Column(nullable = false, unique = true)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(nullable = false)
	public String getParserClass() {
		return parserClass;
	}
	
	public void setParserClass(String parserClass) {
		this.parserClass = parserClass;
	}

	@SuppressWarnings("unchecked")
	@Transient
	public T getParserClassInstance() {
		try {
			return (T) Class.forName(getParserClass()).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
