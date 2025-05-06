package service;

import domain.Produto;
import util.ProdutoUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoService {
    public static void ser(Map<String, List<Produto>> map) {
        ProdutoUtil.ser(map);
    }

    public static Map<String, List<Produto>> unser() {
      if (ProdutoUtil.unser()==null)return new HashMap<>();
      else
        return ProdutoUtil.unser();
    }
}
