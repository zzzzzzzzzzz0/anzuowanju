package org.zhscript.clas;

import org.zhscript.Shell_;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by zzzzzzzzzzz on 17-7-29.
 */

public class Exec_ {
	static class Exec_Opt extends Opt_ {
		String sh, outcode, errcode, dir;
		boolean redirerr;
		ArrayList<String> cmds = new ArrayList<>();
		@Override
		Item_[] items__() {
			return new Item_[] {
					new Item_("-壳", 1, (s) -> {
						sh = s[0];
					}),
					new Item_("-错误", 1, (s) -> {
						errcode = s[0];
					}),
					new Item_("-输出", 1, (s) -> {
						outcode = s[0];
					}),
					new Item_("-目录", 1, (s) -> {
						dir = s[0];
					}),
					new Item_("-合并", () -> {
						redirerr = true;
					}),
					new Item_(1, (s) -> {
						cmds.add(s[0]);
					}),
			};
		}
	}

	static void err__(Exception e, Exec_Opt opt, Zs_ zs, long qv_up, Object resource, I_ i) {
		if(opt.errcode != null)
			zs.i__(opt.errcode, resource, qv_up, e.getLocalizedMessage());
		else
			i.sendMessage__(e);
	}

	public static synchronized String[] exec__(String[] a, Zs_ zs, long qv_up, Object resource, I_ i) {
		qv_up = Shell_.up(qv_up);
		Exec_Opt opt = new Exec_Opt();
		opt.parse__(a);
		Process p = null;
		DataOutputStream os = null;
		int ret = -1;
		BufferedReader br_out = null, br_err = null;
		try {
			ProcessBuilder pb = new ProcessBuilder(opt.sh);
			if(opt.dir != null)
				pb.directory(new File(opt.dir));
			if(opt.redirerr)
				pb.redirectErrorStream(true);
			p = pb.start();

			/*p = Runtime.getRuntime().exec(opt.sh, null, opt.dir == null ? null : new File(opt.dir));*/

			os = new DataOutputStream(p.getOutputStream());
			for(String cmd : opt.cmds) {
				if(cmd == null)
					continue;
				os.write(cmd.getBytes());
				os.writeBytes("\n");
				os.flush();
			}
			//os.writeBytes("\4");
			os.writeBytes("exit\n");
			os.flush();

			ret = p.waitFor();
			if(opt.outcode != null) {
				br_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String s;
				for(; (s = br_out.readLine()) != null;)
					zs.i__(opt.outcode, resource, qv_up, s);
			}
			if(opt.errcode != null) {
				br_err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				String s;
				for(; (s = br_err.readLine()) != null;)
					zs.i__(opt.errcode, resource, qv_up, s);
			}
		} catch (Exception e) {
			err__(e, opt, zs, qv_up, resource, i);
		} finally {
			try {
				if(os != null)
					os.close();
			} catch (Exception e) {err__(e, opt, zs, qv_up, resource, i);}
			try {
				if(br_out != null)
					br_out.close();
			} catch (Exception e) {err__(e, opt, zs, qv_up, resource, i);}
			try {
				if(br_err != null)
					br_err.close();
			} catch (Exception e) {err__(e, opt, zs, qv_up, resource, i);}
			if(p != null)
				p.destroy();
		}
		return new String[] {String.valueOf(ret)};
	}
}
