package test.puzikov.servlets;

import com.google.gson.Gson;
import test.puzikov.exceptions.TestException;
import test.puzikov.models.OutputJSONContainer;
import test.puzikov.models.ServiceXML;
import test.puzikov.models.Transaction;
import test.puzikov.storage.ConcurrentTransactionStorage;
import test.puzikov.utils.xml.XMLParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TransactionsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        StringBuffer sb = new StringBuffer();
        String line;
        BufferedReader br = request.getReader();
        while ((line = br.readLine()) != null) {
            sb.append(line.trim());
        }
        try {
            XMLParser xmlParser = new XMLParser(ServiceXML.class);
            ServiceXML serviceXML = xmlParser.unmarshal(sb.toString());
            String login = serviceXML.getAccountInfo().getLogin();
            ConcurrentTransactionStorage storage = getStorage();
            storage.addToStorage(login, serviceXML);
        } catch (TestException e) {
            response.setStatus(503);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        if (login == null || login.equals("")) {
            response.setStatus(503);
        } else {
            PrintWriter out = response.getWriter();
            long startMsTime = System.currentTimeMillis();
            List<Transaction> transactions = getStorage().getAllTransactionsByLogin(login);
            long stopMsTime = System.currentTimeMillis();
            long processTime = stopMsTime - startMsTime;
            OutputJSONContainer jsonContainer = new OutputJSONContainer();
            jsonContainer.setProcessTime(Long.toString(processTime));
            jsonContainer.setTransactions(transactions);
            Gson gson = new Gson();
            String json = gson.toJson(jsonContainer);
            out.println(json);
        }
    }

    /**
     * Получить хранилище
     * @return ConcurrentTransactionStorage
     */
    private ConcurrentTransactionStorage getStorage() {
        return ConcurrentTransactionStorage.getInstance();
    }
}