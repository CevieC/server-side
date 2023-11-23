package sg.edu.nus.iss.d12lecture.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.d12lecture.exception.randNoException;
import sg.edu.nus.iss.d12lecture.model.Generate;


@Controller
@RequestMapping(path="/rand")
public class generateRandController {
    
    @GetMapping(path="/show")
    public String showRandomForm(Model m){
        Generate g = new Generate();
        m.addAttribute("generateObj", g);
        return "generate";
    }

    @GetMapping(path="/generate")
    public String generate(@RequestParam Integer numberVal, Model m)
    {
        this.randomizeNumber(m, numberVal.intValue());
        return "result";
    }

    private void randomizeNumber(Model m, int noOfGenerateNo)
    {
        int maxGenNo= 31;
        String[] imgNumbers = new String[maxGenNo];
        // validate noOfGenerateNo cannot be less than 1 and gt 30
        if(noOfGenerateNo < 1 || noOfGenerateNo > maxGenNo-1){
            throw new randNoException();
        }

        // Gen number images filename and set to the array.
        for(int x =0; x < maxGenNo; x++){
            if(x>0){
                System.out.print("x> " + x);
                imgNumbers[x] = "number" + x +".jpg";
            }
        }

        List<String> selectedImgs = new ArrayList<String>();
        Random rand = new Random();
        Set<Integer> uniqueResult = new LinkedHashSet<Integer>();
        while(uniqueResult.size() < noOfGenerateNo){
            Integer randNoResult = rand.nextInt(maxGenNo);
            if(randNoResult != null){
                if(randNoResult > 0)
                    uniqueResult.add(randNoResult);
            }
        }

        Integer currElem= null;
        for(Iterator iter = uniqueResult.iterator(); iter.hasNext();){
            currElem = (Integer)iter.next();
            System.out.println(currElem);
            if(currElem != null)
                selectedImgs.add(imgNumbers[currElem]);
        }

        m.addAttribute("noOfGenerateNo", noOfGenerateNo);
        m.addAttribute("selectedImgs", selectedImgs);
        System.out.println(" >>> " + selectedImgs);

    }
}