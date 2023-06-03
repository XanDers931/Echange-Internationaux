package front;

public interface Controller {
	public void initialize() throws Exception;
	public void setSceneWrapperParent(SceneWrapper s);
	public SceneWrapper getSceneWrapperParent();
}
