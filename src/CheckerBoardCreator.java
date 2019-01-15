import java.util.HashMap;

public class CheckerBoardCreator
{
    public static HashMap<String, CheckerBoard> models = new HashMap<String, CheckerBoard>();

    public static CheckerBoard getModel(String s)
    {
        if (models.containsKey(s))
        {
            //System.out.println("Returning existing model");
            return models.get(s);
        }
        else
        {
            CheckerBoard newModel = new CheckerBoard();
            models.put(s, newModel);
            //System.out.println("Returning new model");
            return newModel;
        }
    }
}