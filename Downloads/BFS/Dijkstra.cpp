#include <map>
#include <list>
#include <iostream>
#include <string>
#include <queue>
#include <limits.h>

using namespace std;

struct Graph {
	list<string> vertices;
	map<pair<string,string>,int> edges;
	map<string,list<string> > adjNodes;
	void PrintOut(void);
	void AddEdge(string,string,int);
	bool FindVertex(string);
	int FindEdgeCost(string vert1, string vert2);
	int IsReachable(string vert1, string vert2);
	int Dijkstra(string vert1, string vert2);
};

void Graph::PrintOut(void) {
	list<string>::iterator it;
	map<pair<string,string>,int>::iterator eit;

	for(it=vertices.begin(); it!=vertices.end(); it++) {
		cout << *it << endl;
	}

	for(eit = edges.begin(); eit != edges.end(); eit++) {
		cout << (*eit).first.first << " " << (*eit).first.second << " " << (*eit).second << endl;
	}
}

void Graph::AddEdge(string vert1,string vert2,int weight) {
	if(FindVertex(vert1) && FindVertex(vert2)) {
		pair<string,string> edge (vert1,vert2);
		edges.insert(pair<pair<string,string>, int> (edge, weight));
		
		list<string>* adj = &adjNodes[vert1];
		adj->push_back(vert2);
	}
}

bool Graph::FindVertex(string vert1) {
	list<string>::iterator it;
	for(it=vertices.begin(); it!=vertices.end(); it++) {
		if(vert1 == *it)
			return true;
	}
	return false;
}

int Graph::FindEdgeCost(string vert1, string vert2) {
	map<pair<string,string>,int>::iterator it;
	pair<string,string> foo (vert1, vert2);

	it = edges.find(foo);

	if(it->second > 0 && it->second < 50000)
		return it->second;
	else
		return -1;
}

int Graph::IsReachable(string vert1, string vert2) {
	map<string,string> color;
	map<string,int> d;
	map<string,string> pi;
	queue<string> Q;

	list<string>::iterator it;
	map<pair<string,string>,int>::iterator v;

	for(it=vertices.begin(); it!=vertices.end(); it++) {
		color[*it] = "WHITE";
		d[*it] = INT_MAX;
		pi[*it] = "NIL";
	}
	color[vert1] = "GRAY";
	d[vert1] = 0;
	pi[vert1] = "NIL";
	Q.push(vert1);
	while(!Q.empty()) {
		string u = Q.front();
		Q.pop();
		
		for(v = edges.begin(); v != edges.end(); v++) {
			string adj;
			if(((*v).first).first == u) {
				adj = ((*v).first).second;
				if(color[adj] == "WHITE") {
					color[adj] = "GRAY";
					d[adj] = d[u] + 1;
					pi[adj] = u;
					Q.push(adj);
				}
			}
		}
		color[u] = "BLACK";
	}
	if(d[vert2] >= 0 && d[vert2] < INT_MAX)
		return d[vert2];
	else
		return -1;
}

class weightCompare {
	public:
		bool operator () (pair<string, int>& n0, pair<string, int>& n1){
			if(n0.second > n1.second)	return true;
			return false;
		}
};

int Graph::Dijkstra(string vert1, string vert2) {
	if(!FindVertex(vert1) || !FindVertex(vert2))
		return -2;

	map<string,string> color;
	map<string,int> weight;
	list<string>::iterator it;
	priority_queue<pair<string,int>, vector<pair<string, int> >, weightCompare> Q;

	for(it=vertices.begin(); it!=vertices.end(); it++) {
		weight[*it] = INT_MAX;
		color[*it] = "WHITE";
		pair<string, int> item(*it, INT_MAX);
	}
	weight[vert1] = 0;
	pair<string, int> item(vert1, 0);
	Q.push(item);

	while(!Q.empty()) {
		pair<string, int> u = Q.top();
		Q.pop();

		if(color[u.first] == "WHITE") {
			color[u.first] = "GRAY";
			for(it=adjNodes[u.first].begin(); it!=adjNodes[u.first].end(); it++) {
				int curWeight = edges[pair<string, string>(u.first, *it)] + u.second;
				if (weight[*it] > curWeight) {
					weight[*it] = curWeight;
				}
				if(color[*it] == "WHITE") {
					pair<string, int> newNode(*it, curWeight);
					Q.push(newNode);
				}
			}
			color[u.first] = "BLACK";
		}
	}
	if(weight[vert2] != INT_MAX)
		return weight[vert2];
	else
		return -1;
}

int main() {
	Graph G;
	string name;
	string input;
	int weight;

	cin >> name;

	while (name != "END") {
		G.vertices.push_back(name);

		cin >> name;
	}

	cin >> name;

	while (name != "END") {
		cin >> input;
		cin >> weight;
		G.AddEdge(name,input,weight);

		cin >> name;
	}

	cin >> name;

	while(name != "END") {
		cin >> input;
		weight = G.Dijkstra(name,input);
		
		if (weight == -2)
			cout << "ERROR" << endl;
		else if (weight == -1)
			cout << "INFINITY" << endl;
		else
			cout << weight << endl;
		cin >> name;
	}

	// Leftover from BFS, don't want to delete until end.

	//cin >> weight; // reusing weight. just lazy

	/*
	while(weight != 0) {
		if(weight == 1) {
			cin >> name;
			if(G.FindVertex(name))
				cout << 1 << endl;
			else
				cout << 0 << endl;
		}
		else if(weight == 2) {
			cin >> name;
			cin >> input;

			int cost = G.FindEdgeCost(name,input);
			cout << cost << endl;
		}
		else if(weight == 3) {
			cin >> name;
			cin >> input;

			int cost = G.IsReachable(name,input);
			cout << cost << endl;
		}

		cin >> weight;
	}
	*/

	//G.PrintOut();
}
