
package Showpost;

import controlPost.EditPostServlet;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Supun Athukorala
 */
public class ViewPostServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONParser parser = new JSONParser();
        String postNo = request.getParameter("id");
        try {

           
            FileReader fileReader = new FileReader("../webapps/ProjectBlog/Documents/post" + postNo + ".txt");
                Object readerObj = parser.parse(fileReader);

            JSONObject jsonObject = (JSONObject) readerObj;
            if (jsonObject.containsKey("Page Hit")) {

                String HitCount = (String) jsonObject.get("Page Hit");
                int count = Integer.parseInt(HitCount);
                count++;
                System.out.println(HitCount);
                jsonObject.put("Page Hit", "" + count);
            } else {
                jsonObject.put("Page Hit", "" + 1);

            }
            fileReader.close();
            FileWriter file;

            file = new FileWriter("../webapps/ProjectBlog/Documents/post" + postNo + ".txt");
            try {
                file.write(jsonObject.toJSONString());

            } catch (IOException e) {
            } finally {
                file.flush();
                file.close();

            }
            request.setAttribute("ViewPost", jsonObject);
            request.getRequestDispatcher("viewPost.jsp").forward(request, response);

        } catch (ParseException ex) {
            Logger.getLogger(EditPostServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
