#include<stdio.h>
#include<stdlib.h>
#include<string.h>

   struct stack{
       char ar[1000000];
       int ind;
   }st;
   void push(char b)
   {
       st.ind++;
       st.ar[st.ind]=b;
   }
   void pop()
   {
       st.ind--;
   }
   char top()
   {

       return st.ar[st.ind];

   }

int main()
{

    int n;
  while(scanf("%d\n",&n)!=EOF){
getc(stdin);
        int i,j,f,k=0;
   for(j=0;j<n;j++){
    st.ind=-1;

    char a[1000000];
       scanf("%s",a);
       int l=strlen(a);
       for(i=0;i<l;i++)
       {
k=1;
           if(a[i]=='(')
                push('(');
           else if(a[i]=='[')
            push('[');
           else if(a[i]==')')
           { if(top()!='(')
                {
                    printf("No\n");
                    k=0;
                    break;
                }
                else
                    pop();
           }
           else if(a[i]==']')
           { if(top()!='[')
                {
                    printf("No\n");
                    k=0;
                    break;
                }
                else
                    pop();
           }


       }
       if(l==0)
         printf("Yes\n");
      else if(i==l&&st.ind==-1)
       {

       printf("Yes\n");

       }

       else if(k)
         printf("No\n");
   }
   }
}


