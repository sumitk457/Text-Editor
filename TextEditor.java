import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    //File menu items
    JMenuItem newFile, openFile, saveFile;
    //Edit menu items
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;
    TextEditor(){
        //Initialize frame
        frame = new JFrame();
        //Initialize menuBar
        menuBar = new JMenuBar();
        //Initialize textArea
        textArea = new JTextArea();
        //Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");
        //Initialize File menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        //Adding action listener to File menu items
        newFile.addActionListener(this);
        openFile.addActionListener((this));
        saveFile.addActionListener(this);
        //Adding File menu items to File menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        //Initialize Edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        //Adding action listener to Edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //Adding Edit menu items to Edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        //Add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);
        //Set menuBar to frame
        frame.setJMenuBar(menuBar);
        //Create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add textArea to panel
        panel.add(textArea,BorderLayout.CENTER);
        //Create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add scroll pane to panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);
        //Set frame dimensions
        frame.setBounds(350,150,800,500);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==newFile){
            //Perform newFile operation
            TextEditor newTextEditor = new TextEditor();
        }
        if(actionEvent.getSource()==openFile){
            //Open file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //User clicks on OPEN button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Get selected file
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //Read contents of file line by line
                    while((intermediate = bufferedReader.readLine())!=null){
                        output += intermediate+"\n";
                    }
                    //Set output string to textArea
                    textArea.setText(output);
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            //Open file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            //User clicks on SAVE button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Create new file with chosen path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //Write contents to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==cut){
            //Perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //Perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            //Perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            //Perform selectAll operation
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            //Perform close operation
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}