    package it.unibo.oop.lab.mvc;
    
    import java.util.LinkedList;
    import java.util.List;
    import java.util.Objects;
    
    public class ImplController implements Controller {
    private final List<String> stringHist = new LinkedList<>();
    private String string;

    public void setNextStringToPrint(final String string) {
       this.string = Objects.requireNonNull(stringHist, "not null value");
        }
    
       
    public List<String> getNextStringToPrint() {
            return stringHist;
        }
    
    
        public List<String> getPrintedStringHistory() {
    return stringHist;
          
        }
    
    
        public void printCurrentString() {
        stringHist.add(this.string);
        System.out.println("la stringa corrente è: " +this.string);
    
            
        }
    
    }
