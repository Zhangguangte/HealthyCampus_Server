import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class TestSearch {

	@Test
	public void testSearch() throws SolrServerException {
		CloudSolrServer cloudSolrServer = new CloudSolrServer(
				"192.168.2.102:2181,192.168.2.102:2182,192.168.2.102:2183");
		cloudSolrServer.setDefaultCollection("collection1");
		SolrQuery query = new SolrQuery();
		query.set("df", "di_keywords");
		query.setQuery("*心脏*");
		query.setStart(2);
		query.setRows(5);
		query.setHighlight(true);
		query.addHighlightField("di_intro");
		query.addHighlightField("di_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		QueryResponse response = cloudSolrServer.query(query);
		SolrDocumentList solrDocumentList = response.getResults();
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		for (SolrDocument document : solrDocumentList) {
			System.out.println(document.get("id"));
			System.out.println(document.get("di_title"));
			System.out.println(document.get("di_intro"));
			System.out.println(document.get("di_url"));
			System.out.println(document.get("di_depart"));
			System.out.println(document.get("di_part"));
			// 取高亮结果
			List<String> list = highlighting.get(document.get("id")).get("di_title");
			String itemTitle = "";
			if (list != null && list.size() > 0) {
				itemTitle = list.get(0);
			} else {
				itemTitle = (String) document.get("di_title");
			}
			System.out.println("***********");
		}
	}

}
