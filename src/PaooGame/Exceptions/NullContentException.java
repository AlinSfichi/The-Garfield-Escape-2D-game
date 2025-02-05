package PaooGame.Exceptions;

/*! \public class NullContentException  extends Exception
    \brief Implementeaza notiunea de exceptie de tip continut nul al unui fisier

 */

public class NullContentException  extends Exception{
    /*! \fn public NullContentException(String s)
               \brief Constructorul cu parametri al clasei NullContentException

                \param s Denumirea exceptiei
   */
    public NullContentException(String s)
    {
        super(s);
    }
}
